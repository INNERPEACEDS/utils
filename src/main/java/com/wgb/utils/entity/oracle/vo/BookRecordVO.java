package com.wgb.utils.entity.oracle.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author INNERPEACE
 * @date 2019/4/19 16:28
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRecordVO  extends BaseRowModel {
	@ExcelProperty(value = {"编号"}, index = 0)
	private String id;
	@ExcelProperty(value = {"书籍名称"}, index = 1)
	private String name;
	@ExcelProperty(value = {"备注信息"}, index = 2)
	private String remarks;
	@ExcelProperty(value = {"创建时间"}, index = 3)
	private Date createTime;
	@ExcelProperty(value = {"更新时间"}, index = 4)
	private Date updateTime;
}

