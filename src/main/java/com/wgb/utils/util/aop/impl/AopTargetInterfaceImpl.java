package com.wgb.utils.util.aop.impl;

import com.wgb.utils.util.aop.IAopTargetInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author INNERPEACE
 * @date 2019/8/29 11:40
 */
@Slf4j
@Component
public class AopTargetInterfaceImpl implements IAopTargetInterface {
	@Override
	public void save() {
		log.info("存储AopTarget");
	}
}
