package com.wgb.utils.util.spi.service;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @author INNERPEACE
 * @date 2019/10/22 15:55
 */
@SPI
public interface Robot {
	public void sayHello();
}
