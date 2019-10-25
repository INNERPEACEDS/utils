package com.wgb.utils.util.spi.service.impl;

import com.alibaba.dubbo.common.URL;
import com.wgb.utils.util.spi.entity.Car;
import com.wgb.utils.util.spi.entity.RaceCar;
import com.wgb.utils.util.spi.entity.Wheel;
import com.wgb.utils.util.spi.service.CarMaker;
import com.wgb.utils.util.spi.service.WheelMaker;

/**
 * @author INNERPEACE
 * @date 2019/10/24 17:10
 */
public class RaceCarMaker implements CarMaker {
	WheelMaker wheelMaker;

	// 通过 setter 注入 AdaptiveWheelMaker
	public void setWheelMaker(WheelMaker wheelMaker) {
		this.wheelMaker = wheelMaker;
	}

	@Override
	public Car makeCar(URL url) {
		Wheel wheel = wheelMaker.makeWheel(url);
		return new RaceCar(wheel, "80", "2.2m", "red");
	}


}
