package com.wgb.utils.util.spi;

import com.wgb.utils.util.java.spi.service.Robot;
import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @author INNERPEACE
 * @date 2019/10/22 15:59
 */
public class SPITest {
	@Test
	public void sayHello() {
		ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
		System.out.println("Java SPI");
		serviceLoader.forEach(Robot::sayHello);
	}
}
