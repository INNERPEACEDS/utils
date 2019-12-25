package com.wgb.utils.controller.queue;

import com.wgb.utils.util.jms.service.impl.SendServiceOne;
import com.wgb.utils.util.jms.service.impl.SendServiceTwo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author INNERPEACE
 * @date 2019/11/07
 **/
@Controller
@Slf4j
@RequestMapping("/rabbitMq")
public class QueueController {
    @Autowired
    SendServiceOne sendServiceOne;

    @Autowired
    SendServiceTwo sendServiceTwo;

    @RequestMapping("/page")
    public String rabbitMqPage() {
        return "rabbitMq/rabbitMqIndex";
    }
    @RequestMapping("/queueOne")
    public String queueOne(Model model) {
        String message = "向队列一中添加数据";
        /*if (bindingResult.hasErrors()) {
            log.error("队列一控制器数据绑定异常");
            // return BindingResultUtil.bindingResultHasError(bindingResult, model, "index");
        }*/
        log.info("queueOne控制器");
        sendServiceOne.handler(message);
        model.addAttribute("result", "生产队列一成功");
        return "rabbitMq/rabbitMqIndex";
    }

    @RequestMapping("/queueTwo")
    public String queueTwo(Model model) {
        String message = "向队列二中添加数据";
        /*if (bindingResult.hasErrors()) {
            log.error("队列二控制器数据绑定异常");
            //return BindingResultUtil.bindingResultHasError(bindingResult, model, "index");
        }*/
        log.info("queueTwo控制器");
        sendServiceTwo.handler(message);
        model.addAttribute("result", "生产队列二成功");
        return "rabbitMq/rabbitMqIndex";
    }
}
