package com.wgb.utils.util.spi.service;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;
import com.wgb.utils.util.spi.entity.Wheel;

/**
 * 对扩展接口要加 @SPI 注解
 * 对扩展方法要加 @Adaptive 注解
 * @author INNERPEACE
 * @date 2019/10/24
 */
@SPI
public interface WheelMaker {
	@Adaptive
	Wheel makeWheel(URL url);
}
