package com.wgb.utils.util.spi;

import com.alibaba.dubbo.common.URL;
import com.wgb.utils.util.spi.entity.Car;
import com.wgb.utils.util.spi.service.impl.AdaptiveWheelMaker;
import com.wgb.utils.util.spi.service.impl.RaceCarMaker;

/**
 * @author INNERPEACE
 * @date 2019/10/22
 */
public class SPITest {

	public static void main(String[] args) {
		RaceCarMaker raceCarMaker = new RaceCarMaker();
		raceCarMaker.setWheelMaker(new AdaptiveWheelMaker());
		URL url = URL.valueOf("dubbo://192.168.0.101:20880/XxxService?wheel.maker=MichelinWheelMaker1");
		Car car = raceCarMaker.makeCar(url);
	    System.out.println(car);
	}

}

