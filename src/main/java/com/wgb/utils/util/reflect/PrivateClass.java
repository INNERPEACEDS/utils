package com.wgb.utils.util.reflect;

import com.wgb.utils.entity.user.UserSession;

/**
 * @author INNERPEACE
 * @date 2019/7/2
 **/
public class PrivateClass {

	private String field;

	private void setField(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}

	private String sayHello(String name, UserSession userSession) {
		System.out.println(userSession.toString());return "Hello: " + name;
	}

}
