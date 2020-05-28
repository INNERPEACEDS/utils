package com.wgb.utils.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author INNERPEACE
 * @date 2018/12/19 16:32
 **/
@Slf4j
@Controller
@RequestMapping("")
public class IndexController {
    @RequestMapping("")
    public String defaultIndex() {
        return "index";
    }
    @RequestMapping("/i")
    public String defaultIndex1() {
        return "index";
    }
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}



