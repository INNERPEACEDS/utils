package com.wgb.utils.controller.downloadandupload;

import com.wgb.utils.entity.oracle.dto.BookRecordDTO;
import com.wgb.utils.entity.oracle.BookRecord;
import com.wgb.utils.service.download.DownloadService;
import com.wgb.utils.util.excel.download.BaseInf;
import com.wgb.utils.util.excel.download.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author : innerpeace
 * @date : 2018/12/10 13:47
 */
@Slf4j
@Controller
@RequestMapping("/downloadAndUpload")
public class DownloadBookRecordController {
	/**
	 * 时间格式化
	 */
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");

	@Autowired
	DownloadService downloadService;

	/**
	 * 优秀书籍下载
	 * @return
	 */
	@RequestMapping("/downloadBookRecord")
	public String bookRecord(@Valid BookRecordDTO bookRecordDTO, HttpServletRequest request, HttpServletResponse response) {
		// 文件前缀
		String filePrefix = "优秀书籍";
		// 文件格式后缀
		String fileSuffix = ".xls";
		// 文件名称
		String fileName = filePrefix + sdf.format(new Date());
		// excle中单个表sheet名称
		String sheetName = "书籍推荐";
		try {
			// 获取封装excel中单元格属性名和获取值的对象的集合
			List<BaseInf> baseInfs = downloadService.fillFieldAndTitle();
			// 初始化参数条件

			log.info("下载-查询参数：[id]-[{}],[name]-[{}],[remarks]-[{}]", bookRecordDTO.getId(), bookRecordDTO.getName(), bookRecordDTO.getRemarks());
			// 根据查询条件查询优秀书籍信息
			List<BookRecord> listInfo = downloadService.getBookRecordInfo(bookRecordDTO);
			if (response != null && listInfo != null) {
				org.apache.poi.ss.usermodel.Workbook workbook= ExcelUtil.createWorkbook(2007, sheetName, baseInfs, listInfo);
				ExcelUtil.workbook2InputStream(response, workbook, fileName, fileSuffix);
				return null;
			}
		} catch(Exception e) {
			log.info("下载失败", e);
		}
		return null;
	}
}
