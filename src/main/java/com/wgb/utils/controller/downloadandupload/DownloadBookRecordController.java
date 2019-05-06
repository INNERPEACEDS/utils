package com.wgb.utils.controller.downloadandupload;

import com.github.pagehelper.PageInfo;
import com.wgb.utils.common.mybatis.DynamicDataSourceSwitch;
import com.wgb.utils.entity.oracle.dto.BookRecordDTO;
import com.wgb.utils.entity.oracle.BookRecord;
import com.wgb.utils.entity.oracle.vo.BookRecordVO;
import com.wgb.utils.entity.result.Result;
import com.wgb.utils.service.download.DownloadService;
import com.wgb.utils.service.query.page.PageService;
import com.wgb.utils.util.constants.controller.ControllerConstant;
import com.wgb.utils.util.excel.alibaba.ExcelUtils;
import com.wgb.utils.util.excel.download.BaseInf;
// import com.wgb.utils.util.excel.download.ExcelUtil;
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

	@Autowired
	PageService pageService;

	/**
	 * 优秀书籍下载
	 * @return
	 */
	@RequestMapping("/downloadBookRecord")
	public String downloadBookRecord(@Valid BookRecordDTO bookRecordDTO, HttpServletRequest request, HttpServletResponse response) {
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

			// log.info("下载-查询参数：[id]-[{}],[name]-[{}],[remarks]-[{}]", bookRecordDTO.getId(), bookRecordDTO.getName(), bookRecordDTO.getRemarks());
			log.info("查询参数信息：[编号：{}；名称：{}；备注：{}；日期范围：({}-{}){}-{}]", bookRecordDTO.getId(), bookRecordDTO.getName(), bookRecordDTO.getRemarks(), bookRecordDTO.getStartCreateDate(), bookRecordDTO.getEndCreateDate(), bookRecordDTO.getStartCreateTime(),bookRecordDTO.getStartTime());
			// 根据查询条件查询优秀书籍信息
			DynamicDataSourceSwitch.setRouteKey("slave1");
			List<BookRecord> listInfo = null;

			if ("master".equals(DynamicDataSourceSwitch.getRouteKey())) {
				// 如果是从主库中下载，则使用不完整下载（只下载重要部分）
				log.info("[下载操作]主库查询数据,而且查询信息的约束条件不包含时间");
				listInfo = downloadService.getBookRecordInfo(bookRecordDTO);
			} else {
				// 如果是从从库中下载，则使用完整下载（下载所有数据）
				log.info("[下载操作]从库查询数据");
				Result<List<BookRecord>> result = (Result<List<BookRecord>>)pageService.queryBookRecordByDTO(bookRecordDTO, ControllerConstant.SIGN_DOWNLOAD);
				listInfo = result.getData();
			}
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

	/**
	 * 优秀书籍下载(采用阿里工具)
	 * 需要更换poi版本为4.1.0或者其他支持版本（3.7版本不支持此功能）
	 * @return
	 */
	@RequestMapping("/downloadBookRecord1")
	public String downloadBookRecord1(@Valid BookRecordDTO bookRecordDTO, HttpServletRequest request, HttpServletResponse response) {
		// 文件前缀
		String filePrefix = "优秀书籍";
		// 文件格式后缀
		String fileSuffix = ".xls";
		// 文件名称
		String fileName = filePrefix + sdf.format(new Date());
		// excle中单个表sheet名称
		String sheetName = "书籍推荐";
		try {
			log.info("下载-查询参数：[id]-[{}],[name]-[{}],[remarks]-[{}]", bookRecordDTO.getId(), bookRecordDTO.getName(), bookRecordDTO.getRemarks());
			log.info("查询参数信息：[编号：{}；名称：{}；备注：{}；日期范围：({}-{}){}-{}]", bookRecordDTO.getId(), bookRecordDTO.getName(), bookRecordDTO.getRemarks(), bookRecordDTO.getStartCreateDate(), bookRecordDTO.getEndCreateDate(), bookRecordDTO.getStartCreateTime(),bookRecordDTO.getStartTime());
			// 根据查询条件查询优秀书籍信息
			DynamicDataSourceSwitch.setRouteKey("slave1");
			String type = "1";
			Result<List<BookRecord>> listInfo = (Result<List<BookRecord>>)pageService.queryBookRecordByDTO(bookRecordDTO, type);
			// List<BookRecord> listInfo = downloadService.getBookRecordInfo(bookRecordDTO.);
			ExcelUtils.export(listInfo.getData(), response, BookRecordVO.class);
		} catch(Exception e) {
			log.info("下载失败", e);
			log.error("需要更改poi版本");
		}
		return null;
	}
}
