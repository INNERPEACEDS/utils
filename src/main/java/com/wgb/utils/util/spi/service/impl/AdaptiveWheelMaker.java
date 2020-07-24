package com.wgb.utils.util.spi.service.impl;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.wgb.utils.util.spi.entity.Wheel;
import com.wgb.utils.util.spi.service.WheelMaker;
import lombok.extern.slf4j.Slf4j;

/**
 * @author INNERPEACE
 * @date 2019/10/24
 */
@Slf4j
public class AdaptiveWheelMaker implements WheelMaker {
	@Override
	public Wheel makeWheel(URL url) {
		if (url == null) {
			throw new IllegalArgumentException("url == null");
		}

		// 1.从 URL 中获取 WheelMaker 名称
		String wheelMakerName = url.getParameter("wheel.maker");
		log.info("wheelMakerName={}", wheelMakerName);
		if (wheelMakerName == null) {
			throw new IllegalArgumentException("wheelMakerName == null");
		}

		// 2.通过 SPI 加载具体的 WheelMaker
		// WheelMaker wheelMaker = ExtensionLoader.getExtensionLoader(WheelMaker.class).getExtension(wheelMakerName);
		// 2.通过自适应扩展加载具体的 WheelMaker
		WheelMaker wheelMaker = ExtensionLoader.getExtensionLoader(WheelMaker.class).getAdaptiveExtension();
		// 3.调用目标方法
		// 不能扩展没有添加@Adaptive注解的方法，也不能扩展没有URL参数的方法
//		return wheelMaker.makeNewWheel(url);
		String a = "test";
		return wheelMaker.makeWheel(url);
	}

	@Override
	public Wheel makeNewWheel(URL url) {
		String newWheel =  url.getParameter("wheel.maker");
		Wheel wheel = new Wheel();
		newWheel = newWheel + "AdaptiveWheelMaker";
		wheel.setColor(newWheel);
		return wheel;
	}


}
