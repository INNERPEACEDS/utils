package com.wgb.utils.controller;

import com.wgb.utils.entity.oracle.dto.UserDTO;
import com.wgb.utils.util.binding.result.BindingResultUtil;
import com.wgb.utils.util.constants.result.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author INNERPEACE
 * @date 2019/1/28 17:56
 **/
@Controller
@Slf4j
public class AuthController {
    @RequestMapping("/loginInfo")
    public String loginInfo(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        String message = "提交成功";
        if (bindingResult.hasErrors()) {
            return BindingResultUtil.bindingResultHasError(bindingResult, model, "index");
        }

        log.info("用户信息：[商户姓名：{}，商户年龄：{}，商户性别：{}]", userDTO.getName(), userDTO.getAge(), userDTO.getSex());

        model.addAttribute(Constant.MESSAGE, message);
        return "index";
    }

}
