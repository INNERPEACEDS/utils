package com.wgb.utils.util.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.wgb.utils.util.spi.service.Robot;

/**
 * @author INNERPEACE
 * @date 2019/10/24
 */
public class DubboSPITest {
	public static void main(String[] args) {
		ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
		Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
		optimusPrime.sayHello();
		Robot bumblebee = extensionLoader.getExtension("bumblebee");
		bumblebee.sayHello();
	}
}
