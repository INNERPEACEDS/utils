package com.wgb.utils.util.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author INNERPEACE
 * @date 2019/8/29 11:51
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AopTest {
	// ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	@Resource
	IAopTargetInterface aopTargetInterfaceImpl;
	@Resource
	TargetClass targetClass;

	//目标对象有实现接口，spring会自动选择"jdk代理【动态代理】"
	//动态代理的标识：class com.sun.proxy.$Proxy10
	@Test
	public void testForJdk() {
		// IAopTargetInterface user = (IAopTargetInterface) ac.getBean("aopTargetInterfaceImpl");
		System.out.println("类：" + aopTargetInterfaceImpl.getClass());
		aopTargetInterfaceImpl.save();
	}

	//class com.bie.aop.OrderDao$$EnhancerByCGLIB$$4952a60a
	// 目标对象没有实现接口，spring会用"cglib代理哦"
	@Test
	public void testForCglib() {
		// User user = (User) ac.getBean("user");
		System.out.println(targetClass.getClass());
		targetClass.save();
	}

}
