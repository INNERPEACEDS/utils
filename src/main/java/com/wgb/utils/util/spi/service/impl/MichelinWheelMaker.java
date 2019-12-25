package com.wgb.utils.util.spi.service.impl;

import com.alibaba.dubbo.common.URL;
import com.wgb.utils.util.spi.entity.Wheel;
import com.wgb.utils.util.spi.service.WheelMaker;

/**
 * @author INNERPEACE
 * @date 2019/10/24
 */
public class MichelinWheelMaker implements WheelMaker {

	@Override
	public Wheel makeWheel(URL url) {
		Wheel wheel = new Wheel();
		wheel.setColor("black");
		return wheel;
	}

	@Override
	public Wheel makeNewWheel(String newWheel) {
		Wheel wheel = new Wheel();
		newWheel = newWheel + "MichelinWheelMaker";
		wheel.setColor(newWheel);
		return wheel;
	}
}
