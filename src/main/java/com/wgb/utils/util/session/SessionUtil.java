package com.wgb.utils.util.session;

import com.wgb.utils.common.request.RequestHolder;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * @author INNERPEACE
 * @date 2019/5/16 18:29
 **/
public class SessionUtil {
	/**
	 * 设置登录菜单
	 * @param permissionList 登录菜单列表
	 */
	public static void setLogInMenu(List<String> permissionList){
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		httpSession.setAttribute("LOGIN_MENU", permissionList);
	}

	/**
	 * 设置用户信息
	 * @param userInfo 用户信息
	 */
	public static void setMerSysUser(String userInfo){
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		httpSession.setAttribute("USER_INFO", userInfo);
	}

	/**
	 * 获取用户信息
	 * @return 用户信息
	 */
	public static String getUserInfo(){
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		return (String) httpSession.getAttribute("USER_INFO");
	}

	/**
	 * 设置当前角色允许通过的列表
	 * @param set url列表
	 */
	public static void setAllowUrls(Set<String> set){
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		httpSession.setAttribute("ALLOW_URL",set);
	}

	public static Set<String> getAllowUrls(){
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		return (Set<String>) httpSession.getAttribute("ALLOW_URL");
	}

	/**
	 * 保存用户浏览器信息
	 * @param userAgent url列表
	 */
	public static void setUserAgent(String userAgent){
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		httpSession.setAttribute("USER_AGENT",userAgent);
	}

	public static String getUserAgent(){
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		return (String) httpSession.getAttribute("USER_AGENT");
	}
	/**
	 * 保存用户登录ip地址
	 * @param ip url列表
	 */
	public static void setIp(String ip){
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		httpSession.setAttribute("LOGIN_IP",ip);
	}

	public static String getIp(){
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		return (String) httpSession.getAttribute("LOGIN_IP");
	}

	/**
	 * 设置错误信息
	 * @param msg 错误信息
	 */
	public static void setErrorMessage(String msg){
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		httpSession.setAttribute("errorMsg",msg);
	}

	/**
	 * 获取错误信息
	 * @return
	 */
	public static String getErrorMessage() {
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		String errorMsg = (String) httpSession.getAttribute("errorMsg");
		return  errorMsg;
	}

	/**
	 * 移除错误信息
	 */
	public static void removeErrorMessage() {
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		httpSession.removeAttribute("errorMsg");
	}

	/**
	 * 将session清空
	 */
	public static void sessionClear(){
		HttpSession httpSession = RequestHolder.getCurrentHttpSession();
		httpSession.invalidate();
	}

}
