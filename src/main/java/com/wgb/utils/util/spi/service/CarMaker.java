package com.wgb.utils.util.spi.service;

import com.alibaba.dubbo.common.URL;
import com.wgb.utils.util.spi.entity.Car;

/**
 * @author INNERPEACE
 * @date 2019/10/24
 */
public interface CarMaker {
	Car makeCar(URL url);
}
