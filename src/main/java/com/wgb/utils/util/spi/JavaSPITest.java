package com.wgb.utils.util.spi;

import com.wgb.utils.util.spi.service.Robot;

import java.util.ServiceLoader;

/**
 * @author INNERPEACE
 * @date 2019/10/24
 */
public class JavaSPITest {
	public static void main(String[] args ) {
		ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
		System.out.println("Java SPI");
		serviceLoader.forEach(Robot::sayHello);
	}
}
