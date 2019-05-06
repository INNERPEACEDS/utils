package com.wgb.utils.service.download.impl;

import com.wgb.utils.dao.oracle.BookRecordMapper;
import com.wgb.utils.entity.oracle.dto.BookRecordDTO;
import com.wgb.utils.entity.oracle.BookRecord;
import com.wgb.utils.entity.oracle.BookRecordExample;
import com.wgb.utils.service.download.DownloadService;
import com.wgb.utils.util.data.handle.DownloadDataHandling;
import com.wgb.utils.util.excel.download.BaseInf;
import com.wgb.utils.util.path.PathUtil;
import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : innerpeace
 * @date : 2018/11/30 10:52
 */
@Slf4j
@Service
public class DownloadServiceImpl implements DownloadService {
	@Value("${download.file.name}")
	String file;

	@Value("${download.file.path}")
	String path;

	@Autowired
	PathUtil pathUtil;

	@Autowired
	private BookRecordMapper bookRecordMapper;

	@Override
	public void downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response) {
		String pub = "pubKey";
		String pri = "priKey";
		String pubKey = pathUtil.getPubKey();
		log.info("pubKey:{}",pubKey);
		String priKey = pathUtil.getPriKey();
		if(pub.equals(fileName)) {
			fileName = pubKey;
		}
		if (pri.equals(fileName)) {
			fileName = priKey;
		}
		log.info("path:{},file:{}", path, file);
		String realPath = pathUtil.getRealPath();
		download(request, response, path, file);

	}

	public static void download(HttpServletRequest request, HttpServletResponse response, String path, String fileName) {
		//设置文件路径
		log.info("开始访问");
		String realPath = DownloadService.class.getClass().getResource("/").getPath() + path;

		File file = new File(realPath, fileName);
		if (!file.exists()) {
			log.info("文件不存在！");
			return;
		}

		//获取浏览器信息
		String agent = request.getHeader("USER-AGENT");
		log.info("浏览器信息：{}",agent);

		//解决文件名中文乱码，谷歌、火狐等主流浏览器
		if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
			try {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				log.info("文件名格式转换失败！");
			}
		}

		// 设置强制下载不打开
		response.setContentType("application/force-download");
		// 设置文件名
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		byte[] buffer = new byte[1024];
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
			OutputStream os = response.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
			log.info("成功下载！");
		} catch (Exception e) {
			log.info("下载文件出错!");
		}
	}

	@Override
	public List<BaseInf> fillFieldAndTitle() {
		List<BaseInf> baseInfs = new ArrayList<>();
		BaseInf baseInf;
		Method bookIndexConversion = null;
		String methodName = "bookIndexConversion";
		try {
			bookIndexConversion = DownloadDataHandling.class.getDeclaredMethod(methodName, String.class);
		} catch (NoSuchMethodException e) {
			log.info("DownloadDataHandling类中不存在[{}]方法，请检查！", methodName);
		}
		baseInf = new BaseInf("序号", "getId", DownloadDataHandling.class, bookIndexConversion);
		// baseInf = new BaseInf("序号", "getId");
		baseInfs.add(baseInf);
		baseInf = new BaseInf("书籍名称", "getName");
		baseInfs.add(baseInf);
		baseInf = new BaseInf("备注", "getRemarks");
		baseInfs.add(baseInf);
		baseInf = new BaseInf("创建时间", "getUpdateTime");
		baseInfs.add(baseInf);
		baseInf = new BaseInf("更新时间", "getCreateTime");
		baseInfs.add(baseInf);
		return baseInfs;
	}

	@Override
	public List<BookRecord> getBookRecordInfo(BookRecordDTO bookRecordDTO) {
		if (StringUtil.isEmpty(bookRecordDTO.getId())) {
			bookRecordDTO.setId(null);
		}
		if (StringUtil.isEmpty(bookRecordDTO.getName())) {
			bookRecordDTO.setName(null);
		}
		if (StringUtil.isEmpty(bookRecordDTO.getRemarks())) {
			bookRecordDTO.setRemarks(null);
		}
		List<BookRecord> bookRecords = bookRecordMapper.getBookRecordByDTO(bookRecordDTO);
		log.info("查询结束");
		int count = 0;
		for (BookRecord bookRecord : bookRecords) {
			log.info("第{}条数据：{}", ++count, bookRecord);
		}
		if (bookRecords != null && bookRecords.size() > 0) {
			return bookRecords;
		}
		log.info("无数据");
		return null;
	}
}
