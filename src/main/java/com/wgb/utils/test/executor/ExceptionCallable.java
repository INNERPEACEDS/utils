package com.wgb.utils.test.executor;

/**
 * 输出名字长度，返回名字
 * @author INNERPEACE
 * @date 2020/6/4
 */
import java.util.concurrent.Callable;

public class ExceptionCallable implements Callable<String> {

	private String name = null;

	public ExceptionCallable() {

	}

	public ExceptionCallable(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		System.out.println("begin to ExceptionCallable.");

		System.out.println(name.length());

		System.out.println("end to ExceptionCallable.");

		return name;
	}

}
