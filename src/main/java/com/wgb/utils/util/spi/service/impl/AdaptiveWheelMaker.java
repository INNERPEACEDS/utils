package com.wgb.utils.util.spi.service.impl;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.wgb.utils.util.spi.entity.Wheel;
import com.wgb.utils.util.spi.service.WheelMaker;

/**
 * @author INNERPEACE
 * @date 2019/10/24
 */
public class AdaptiveWheelMaker implements WheelMaker {
	@Override
	public Wheel makeWheel(URL url) {
		if (url == null) {
			throw new IllegalArgumentException("url == null");
		}

		// 1.从 URL 中获取 WheelMaker 名称
		String wheelMakerName = url.getParameter("wheel.maker");
		if (wheelMakerName == null) {
			throw new IllegalArgumentException("wheelMakerName == null");
		}

		// 2.通过 SPI 加载具体的 WheelMaker
		// WheelMaker wheelMaker = ExtensionLoader.getExtensionLoader(WheelMaker.class).getExtension(wheelMakerName);
		// 2.通过自适应扩展加载具体的 WheelMaker
		WheelMaker wheelMaker = ExtensionLoader.getExtensionLoader(WheelMaker.class).getAdaptiveExtension();
		// 3.调用目标方法
		return wheelMaker.makeWheel(url);
	}
}
