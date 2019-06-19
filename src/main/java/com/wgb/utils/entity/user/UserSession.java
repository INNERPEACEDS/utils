package com.wgb.utils.entity.user;

import java.util.Set;

/**
 * @author INNERPEACE
 * @date 2019/5/16 18:33
 **/
public class UserSession {
	/**
	 * 用户session 在redis中的前缀
	 */
	public static final String SESSION_REDIS_KEY = "MerSysUserSession_";
	/**
	 * 超时间隔
	 */
	public static final Long SESSION_TIME_OUT = 1800000L;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 用户token
	 */
	private String token;

	/**
	 * 超时时间
	 */
	private Long timeout;

	/**
	 * 用户允许访问的URL
	 */
	private Set<String> authUrl;

	/**
	 * 获取超时时间
	 *
	 * @return 超时时间
	 */
	public static Long getNextTimeOut() {
		return System.currentTimeMillis() + SESSION_TIME_OUT;
	}


}
