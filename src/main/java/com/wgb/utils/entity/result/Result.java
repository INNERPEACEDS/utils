package com.wgb.utils.entity.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author : innerpeace
 * @date : 2018/11/21 10:02
 */
@Data
public class Result<T> {
	/**
	 * 结果代码
	 */
	private String code;

	/**
	 * 结果消息
	 */
	private String msg;

	/**
	 * 结果数据
	 */
	private T data;

	public Result(ResultEnum resultEnum) {
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMsg();
		this.data = null;
	}

	public Result(ResultEnum resultEnum, T data) {
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMsg();
		this.data = data;
	}

	public Result(ResultEnum resultEnum, String msg, T data) {
		this.code = resultEnum.getCode();
		this.msg = msg;
		this.data = data;
	}

	public Result(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 是否响应成功
	 * @return 结果
	 */
	public boolean isOk(){
		return ResultEnum.SUCCESS.getCode().equals(code);
	}

	@JsonIgnore
	public boolean isFail(){
		return !isOk();
	}
}
