package com.wgb.utils.util.xml.entity;

import com.wgb.utils.util.xml.Xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author INNERPEACE
 * @date 2019/12/18 16:17
 */
public class LoadWorld {
	private World world;

	private Map<String, Human> humanMap;

	private Map<String, Forest> forestMap;



	public static class WorldFactory {
		private static LoadWorld loadWorld = new LoadWorld();
	}
	public static LoadWorld getInstance() {
		return WorldFactory.loadWorld;
	}

	private LoadWorld() {}

	public void load(String xmlString) {
		// 解析配置xml
		world = (World) new Xml(World.class).xml2Object(xmlString);
		humanMap = new HashMap<>();
		forestMap = new HashMap<>();
		// 循环加载定时任务
		List<Human> humanList = world.getHumans();
		for (Human human : humanList) {
			humanMap.put(human.getAction(), human);
		}
		Forest forest = world.getForest();
		forestMap.put("forest", forest);
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Map<String, Forest> getForestMap() {
		return forestMap;
	}

	public void setForestMap(Map<String, Forest> forestMap) {
		this.forestMap = forestMap;
	}

	public Map<String, Human> getHumanMap() {
		return humanMap;
	}

	public void setHumanMap(Map<String, Human> humanMap) {
		this.humanMap = humanMap;
	}

}
