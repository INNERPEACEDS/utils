package com.wgb.utils.common.request;

import com.wgb.utils.entity.user.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author INNERPEACE
 * @date 2019/5/16 18:31
 **/
public class RequestHolder {
	/**
	 * 保存请求request对象
	 */
	private static final ThreadLocal<HttpServletRequest> REQUEST_THREAD_LOCAL = new ThreadLocal<>();

	public static void add(HttpServletRequest request) {
		REQUEST_THREAD_LOCAL.set(request);
	}

	public static HttpServletRequest getCurrentRequest() {
		return REQUEST_THREAD_LOCAL.get();
	}

	public static HttpSession getCurrentHttpSession(){
		return REQUEST_THREAD_LOCAL.get().getSession();
	}

	/**
	 * 单次请求中保存该次请求所对应的session
	 */
	private static final ThreadLocal<UserSession> SESSION_THREAD_LOCAL = new ThreadLocal<>();

	public static void addUserSession(UserSession userSession) {
		SESSION_THREAD_LOCAL.set(userSession);
	}

	public static UserSession getUserSession() {
		return SESSION_THREAD_LOCAL.get();
	}

	public static void remove() {
		REQUEST_THREAD_LOCAL.remove();
		SESSION_THREAD_LOCAL.remove();
	}
}
