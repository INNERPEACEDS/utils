package com.wgb.utils.controller.json;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/json")
@Controller
public class JsonController {
    /**
     * json数据主页
     * @return
     */
    @RequestMapping("/page")
    public String JsonJumpPage() {
        return "json/index";
    }

    @RequestMapping("/addJson")
    public String addObjectJson() {
        return null;
    }
}
