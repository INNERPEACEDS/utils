package com.wgb.utils.service.query.page.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgb.utils.dao.oracle.BookRecordMapper;
import com.wgb.utils.entity.oracle.BookRecord;
import com.wgb.utils.entity.oracle.dto.BookRecordDTO;
import com.wgb.utils.entity.result.Result;
import com.wgb.utils.entity.result.ResultEnum;
import com.wgb.utils.service.query.page.PageService;
import com.wgb.utils.util.constants.controller.ControllerConstant;
import com.wgb.utils.util.date.DateUtil;
import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author : innerpeace
 * @date : 2018/12/11 16:11
 */
@Slf4j
@Service
public class PageServiceImpl implements PageService {
	@Autowired
	private BookRecordMapper bookRecordMapper;

	@Override
	public Result<?> queryBookRecordByDTO(BookRecordDTO bookRecordDTO, String type) {
		// 处理空数据（获取的值可能为"",数据库中查询无结果，应该处理数据）
		if (StringUtil.isBlankParam(bookRecordDTO.getId())) {
			bookRecordDTO.setId(null);
		}
		if (StringUtil.isBlankParam(bookRecordDTO.getName())) {
			bookRecordDTO.setName(null);
		}
		if (StringUtil.isBlankParam(bookRecordDTO.getRemarks())) {
			bookRecordDTO.setRemarks(null);
		}
		if (StringUtil.isBlankParam(bookRecordDTO.getStartCreateDate())) {
			bookRecordDTO.setStartCreateDate(DateUtil.toStr(new Date(), DateUtil.DATE_FORMAT));
			// bookRecordDTO.setStartCreateDate(null);
		}
		if (StringUtil.isBlankParam(bookRecordDTO.getEndCreateDate())) {
			bookRecordDTO.setEndCreateDate(DateUtil.toStr(new Date(), DateUtil.DATE_FORMAT));
			// bookRecordDTO.setEndCreateDate(null);
		}
		if (StringUtil.isBlankParam(bookRecordDTO.getStartCreateTime())) {
			bookRecordDTO.setStartCreateTime(DateUtil.TIME_START);
			// bookRecordDTO.setStartCreateTime(null);
		}
		if (StringUtil.isBlankParam(bookRecordDTO.getEndCreateTime())) {
			bookRecordDTO.setEndCreateTime(DateUtil.TIME_END);
			// bookRecordDTO.setEndCreateTime(null);
		}
		log.info("startCreateDate-startCreateTime:[{}]-[{}];endCreateDate-endCreateTime[{}]-[{}]", bookRecordDTO.getStartCreateDate(), bookRecordDTO.getStartCreateTime(), bookRecordDTO.getEndCreateDate(), bookRecordDTO.getEndCreateTime());
		try {
			if (!StringUtil.isBlankParam(bookRecordDTO.getStartCreateDate()) && !StringUtil.isBlankParam(bookRecordDTO.getStartCreateTime())) {
				Date startTime = DateUtil.toDate(bookRecordDTO.getStartCreateDate() + " " + bookRecordDTO.getStartCreateTime(), DateUtil.FULL_FORMAT);
				bookRecordDTO.setStartTime(startTime);
				// 通过查询范围查询，则不需要分段开始时间值
				bookRecordDTO.setStartCreateDate(null);
				bookRecordDTO.setStartCreateTime(null);
			} else {
				bookRecordDTO.setStartTime(null);
			}

			if (!StringUtil.isBlankParam(bookRecordDTO.getEndCreateDate()) && !StringUtil.isBlankParam(bookRecordDTO.getEndCreateTime())) {
				Date endTime = DateUtil.toDate(bookRecordDTO.getEndCreateDate() + " " + bookRecordDTO.getEndCreateTime(), DateUtil.FULL_FORMAT);
				bookRecordDTO.setEndTime(endTime);
				// 通过查询范围查询，则不需要分段结束时间值
				bookRecordDTO.setEndCreateDate(null);
				bookRecordDTO.setEndCreateTime(null);
			} else {
				bookRecordDTO.setEndTime(null);
			}
			log.info("当前页：{}；每页大小：{}", bookRecordDTO.getPageNum(), bookRecordDTO.getPageSize());
            log.info("[开始时间范围：{}]-[结束时间范围：{}]", bookRecordDTO.getStartTime(), bookRecordDTO.getEndTime());
            // log.info("对应值比较[{}]-[{}]", bookRecordDTO.getStartTime(), bookRecordDTO.getEndTime() == "");
			// 分页
			if (ControllerConstant.SIGN_QUERY.equals(type)) {
				PageHelper.startPage(bookRecordDTO.getPageNum(), bookRecordDTO.getPageSize());
			}
			List<BookRecord> list = bookRecordMapper.getBookRecordByDTO(bookRecordDTO);
			int i = 0;
			for (BookRecord bookRecord : list) {
				log.info("数据{}：{}", ++i, bookRecord);
			}
			if (ControllerConstant.SIGN_QUERY.equals(type)) {
				return new Result<>(ResultEnum.SUCCESS, new PageInfo<>(list));
			}
			return new Result<>(ResultEnum.SUCCESS, list);
		} catch (Exception e) {
			log.error("书籍查询异常，查询条件{}", bookRecordDTO, e);
		}
		return new Result<>(ResultEnum.FAIL);
	}

	@Override
	public Result<List<BookRecord>> queryBookRecordByBookRecord(BookRecord bookRecord) {
		return null;
	}
}
