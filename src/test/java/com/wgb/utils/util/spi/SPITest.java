package com.wgb.utils.util.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.wgb.utils.util.annotation.PasswordUtils;
import com.wgb.utils.util.spi.service.Robot;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ServiceLoader;

/**
 * @author INNERPEACE
 * @date 2019/10/22 15:59
 */
public class SPITest {
	@Test
	public void javaSPI() {
		ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
		System.out.println("Java SPI");
		serviceLoader.forEach(Robot::sayHello);
	}

	@Test
	public void dubboSPI() {
		ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
		Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
		optimusPrime.sayHello();
		Robot bumblebee = extensionLoader.getExtension("bumblebee");
		bumblebee.sayHello();
		// Robot trueRobot = extensionLoader.getExtension("true");
		// trueRobot.sayHello();
	}

	@Test
	public void readClassPathTest() throws IOException {
		ClassLoader classLoader = PasswordUtils.class.getClassLoader();
		String fileName = "file/attachment/附件 .png";
		Enumeration urls;
		if (classLoader != null) {
			urls = classLoader.getResources(fileName);
		} else {
			urls = ClassLoader.getSystemResources(fileName);
		}
		while (urls.hasMoreElements()) {
			URL url = (URL) urls.nextElement();
			System.out.println(url);
		}
	}
}
