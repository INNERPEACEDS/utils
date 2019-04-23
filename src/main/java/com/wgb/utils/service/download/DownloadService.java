package com.wgb.utils.service.download;

import com.wgb.utils.entity.oracle.dto.BookRecordDTO;
import com.wgb.utils.entity.oracle.BookRecord;
import com.wgb.utils.entity.oracle.vo.BookRecordVO;
import com.wgb.utils.util.excel.download.BaseInf;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 下载文件业务接口
 * @author : innerpeace
 * @date : 2018/11/30 10:50
 */
public interface DownloadService {
	/**
	 * 下载普通文件
	 * @param fileName
	 * @param request
	 * @param response
	 */
	void downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 初始化excle列名和获取值方法
	 * @return
	 */
	List<BaseInf> fillFieldAndTitle();

	/**
	 * 获取优秀书籍信息
	 * @param bookRecordDTO
	 * @return
	 */
	List<BookRecord> getBookRecordInfo(BookRecordDTO bookRecordDTO);

}
