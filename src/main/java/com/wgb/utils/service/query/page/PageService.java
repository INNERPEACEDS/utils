package com.wgb.utils.service.query.page;


import com.github.pagehelper.PageInfo;
import com.wgb.utils.entity.oracle.BookRecord;
import com.wgb.utils.entity.oracle.dto.BookRecordDTO;
import com.wgb.utils.entity.result.Result;

/**
 * @author : innerpeace
 * @date : 2018/12/11 16:06
 */
public interface PageService {

	/**
	 * 分页查询优秀书籍
	 * @param bookRecordDTO
	 * @return
	 */
	Result<PageInfo<BookRecord>> queryBookRecordByDTO(BookRecordDTO bookRecordDTO);
}
