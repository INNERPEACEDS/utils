package com.wgb.utils.util.spi.service.impl;

import com.wgb.utils.util.spi.service.Robot;

/**
 * @author INNERPEACE
 * @date 2019/10/22 15:56
 */
public class OptimusPrime implements Robot {

	@Override
	public void sayHello() {
		System.out.println("Hello, I am Optimus Prime.");
	}
}
