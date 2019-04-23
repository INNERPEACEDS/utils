package com.wgb.utils.service.query.page;

import com.wgb.utils.entity.oracle.BookRecord;
import com.wgb.utils.entity.oracle.dto.BookRecordDTO;
import com.wgb.utils.entity.result.Result;
import java.util.List;

/**
 * @author : innerpeace
 * @date : 2018/12/11 16:06
 */
public interface PageService {

	/**
	 * 分页查询优秀书籍
	 * @param bookRecordDTO
	 * @param type “0”=查询；“1”=下载
	 * @return
	 */
	Result<?> queryBookRecordByDTO(BookRecordDTO bookRecordDTO, String type);

	Result<List<BookRecord>> queryBookRecordByBookRecord(BookRecord bookRecord);

}
