package com.wgb.utils.util.spi.service.impl;

import com.alibaba.dubbo.common.URL;
import com.wgb.utils.util.spi.entity.Wheel;
import com.wgb.utils.util.spi.service.WheelMaker;
import lombok.extern.slf4j.Slf4j;

/**
 * @author INNERPEACE
 * @date 2019/10/24
 */
@Slf4j
public class MichelinWheelMaker implements WheelMaker {

	@Override
	public Wheel makeWheel(URL url) {
		log.info("MichelineWheelMaker开始");
		Wheel wheel = new Wheel();
		wheel.setColor("black");
		return wheel;
	}

	@Override
	public Wheel makeNewWheel(URL url) {
		String newWheel =  url.getParameter("wheel.maker");
		Wheel wheel = new Wheel();
		newWheel = newWheel + "MichelinWheelMaker";
		wheel.setColor(newWheel);
		return wheel;
	}
}
