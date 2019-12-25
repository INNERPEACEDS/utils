package com.wgb.utils.controller;

import com.alibaba.fastjson.JSONObject;
import com.wgb.utils.util.xml.entity.Forest;
import com.wgb.utils.util.xml.entity.Human;
import com.wgb.utils.util.xml.entity.LoadWorld;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author INNERPEACE
 * @date 2019/12/18 16:47
 */
@Controller
@Slf4j
public class WorldController {
	@RequestMapping("/getWorld")
	public @ResponseBody String getWorld(Model model) {
		Map<String, Human> humanMap = LoadWorld.getInstance().getHumanMap();
		Map<String, Forest> forestMap = LoadWorld.getInstance().getForestMap();
		JSONObject json = new JSONObject();
		humanMap.forEach(json::put);
		forestMap.forEach(json::put);
		return json.toJSONString();
	}
}
