package com.wgb.utils.util.aop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author INNERPEACE
 * @date 2019/8/29 11:54
 */
@Slf4j
@Component
public class TargetClass {
	public void save() {
		log.info("执行TargetClass类save()方法");
	}
}
