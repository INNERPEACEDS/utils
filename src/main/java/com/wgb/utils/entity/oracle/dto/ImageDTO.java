package com.wgb.utils.entity.oracle.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author INNERPEACE
 * @date 2019/4/23 15:37
 **/
@Data
public class ImageDTO {
	private String id;
	private String serial;
	private String name;
	private String address;
	private String unionId;
	private String description;
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

	@Override
	public String toString() {
		return "ImageDTO{" +
				"id='" + id + '\'' +
				", serial='" + serial + '\'' +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", unionId='" + unionId + '\'' +
				", description='" + description + '\'' +
				", startCreateDate='" + startCreateDate + '\'' +
				", endCreateDate='" + endCreateDate + '\'' +
				", startCreateTime='" + startCreateTime + '\'' +
				", endCreateTime='" + endCreateTime + '\'' +
				'}';
	}
}
