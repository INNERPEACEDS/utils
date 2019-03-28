package com.wgb.utils.service.add;

import com.wgb.utils.entity.oracle.BookRecord;
import com.wgb.utils.entity.result.Result;

/**
 * @author : innerpeace
 * @date : 2018/12/14 16:00
 */
public interface AddService {
	/**
	 * 批量对未写明创建时间的数据添加创建时间和更新时间
	 * @return
	 */
	Result batchAddCreateDate();

	/**
	 * 添加书籍
	 * @param bookRecord
	 * @return
	 */
	Result addBookRecord(BookRecord bookRecord);

	/**
	 * 通过书籍id删除书籍
	 * @param id
	 * @return
	 */
	Result deleteBookRecord(String id);
}
