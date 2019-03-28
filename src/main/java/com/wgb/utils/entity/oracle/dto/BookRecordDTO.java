package com.wgb.utils.entity.oracle.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author : innerpeace
 * @date : 2018/12/10 14:24
 */
@Data
public class BookRecordDTO {
	private String id;

	private String name;

	private String remarks;

	private String createTime;

	private String updateTime;

	private String startCreateDate;

	private String endCreateDate;

	private String startCreateTime;

	private String endCreateTime;



	private Date startTime;
	private Date endTime;

	/**
	 * 当前页数
	 */
	private Integer pageNum;

	/**
	 * 每页大小（几行数据）
	 */
	private Integer pageSize;
}
