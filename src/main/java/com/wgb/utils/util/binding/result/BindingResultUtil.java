package com.wgb.utils.util.binding.result;

import com.wgb.utils.entity.result.Result;
import com.wgb.utils.entity.result.ResultEnum;
import com.wgb.utils.util.constants.result.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * spring前台绑定数据参数检验工具类
 * @author : innerpeace
 * @date : 2018/12/14 16:26
 */
@Slf4j
public class BindingResultUtil {

	/**
	 * 绑定数据有错，返回类型为json数据
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	public static Result bindingResultHasError(BindingResult bindingResult, Model model) {
		FieldError fieldError = bindingResult.getFieldErrors().get(0);
		log.info("参数校验结果：{}", fieldError.getDefaultMessage());
		model.addAttribute(Constant.ERROR_MESSAGE, fieldError.getDefaultMessage());
		return new Result(ResultEnum.FAIL);
	}

	/**
	 * 绑定数据有错，返回类型为页面
	 * @param bindingResult
	 * @param model
	 * @param jumpPage
	 * @return
	 */
	public static String bindingResultHasError(BindingResult bindingResult, Model model, String jumpPage) {
		FieldError fieldError = bindingResult.getFieldErrors().get(0);
		log.info("参数校验结果：{}", fieldError.getDefaultMessage());
		model.addAttribute(Constant.MESSAGE, fieldError.getDefaultMessage());
		return jumpPage;
	}
}
