package com.wgb.utils.util.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author INNERPEACE
 * @date 2019/8/29 11:32
 */
// 加入到IoC容器
@Component
// 指定当前类为切面类
@Aspect
public class PointCut {
	// 指定切入点表达式，拦截那些方法，即为那些类生成代理对象
	// @Pointcut("execution(* com.bie.aop.TargetClass.save(..))")  ..代表所有参数
	// @Pointcut("execution(* com.bie.aop.TargetClass.*())")  指定所有的方法
	// @Pointcut("execution(* com.bie.aop.TargetClass.save())") 指定save方法

	@Pointcut("execution(* com.wgb.utils.util.aop.*.*(..))")
    public void pointCut(){
		// System.out.println("执行pointCut方法");
	}

	@Before("pointCut()")
     public void begin(){
		System.out.println("开启事务");
	}

	@After("pointCut()")
    public void close(){
		System.out.println("关闭事务");
	}
}
