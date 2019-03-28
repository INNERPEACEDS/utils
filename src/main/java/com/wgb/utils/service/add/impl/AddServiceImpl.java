package com.wgb.utils.service.add.impl;

import com.wgb.utils.dao.oracle.BookRecordMapper;
import com.wgb.utils.entity.oracle.BookRecord;
import com.wgb.utils.entity.oracle.BookRecordExample;
import com.wgb.utils.entity.oracle.dto.BookRecordDTO;
import com.wgb.utils.entity.result.Result;
import com.wgb.utils.entity.result.ResultEnum;
import com.wgb.utils.service.add.AddService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author : innerpeace
 * @date : 2018/12/14 16:03
 */
@Slf4j
@Service
public class AddServiceImpl implements AddService {
	@Autowired
	BookRecordMapper bookRecordMapper;

    @Override
    public Result batchAddCreateDate() {
        BookRecordExample bookRecordExample = new BookRecordExample();
        bookRecordExample.createCriteria().andCreateTimeIsNull();
        List<BookRecord> bookRecords = bookRecordMapper.selectByExample(bookRecordExample);
        for (BookRecord bookRecord : bookRecords) {
            log.info("获取到的数据{}", bookRecord.toString());
            log.info("开始修改......");
            Date date = new Date();
            bookRecord.setCreateTime(date);
            bookRecord.setUpdateTime(date);
            int updateResult = bookRecordMapper.updateByPrimaryKeySelective(bookRecord);
            if (updateResult <= 0) {
                log.error("批量添加创建时间和更新时间异常");
                return new Result(ResultEnum.EXCEPTION);
            }
        }
        log.info("添加数据成功");
        return new Result(ResultEnum.SUCCESS);
    }

    @Override
	public Result addBookRecord(BookRecord bookRecord) {
		int addResult = bookRecordMapper.insertSelective(bookRecord);
		if (addResult <= 0) {
			log.info("添加数据异常");
			return new Result(ResultEnum.EXCEPTION);
		}
		log.info("添加数据成功");
		return new Result(ResultEnum.SUCCESS);
	}

    @Override
    public Result deleteBookRecord(String id) {
        BookRecordExample bookRecordExample = new BookRecordExample();
        bookRecordExample.createCriteria().andIdEqualTo(id);
        int deleteResult = bookRecordMapper.deleteByExample(bookRecordExample);
        if (deleteResult <= 0) {
            log.info("删除数据异常");
            return new Result(ResultEnum.EXCEPTION);
        }
        log.info("删除数据成功");
        return new Result(ResultEnum.SUCCESS);
    }
}
