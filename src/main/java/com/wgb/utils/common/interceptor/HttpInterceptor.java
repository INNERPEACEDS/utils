package com.wgb.utils.common.interceptor;

import com.wgb.utils.api.url.BaseUrl;
import com.wgb.utils.common.request.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 请求拦截器
 * 需要到WebMvcConfig中注册该拦截器生效
 * @author INNERPEACE
 * @date 2019/5/16 19:12
 **/
@Slf4j
@Component
public class HttpInterceptor implements HandlerInterceptor {
	private static final String REQUEST_TIME = "requestTime";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		log.info("HttpInterceptor");
		// 1.打印请求参数
		String requestURI = request.getRequestURI();
		log.info("请求URL：{}", requestURI);
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
		Iterator<Map.Entry<String, String[]>> iterator = entries.iterator();
		if (log.isDebugEnabled()) {
			iterator.forEachRemaining((entry) -> log.error("参数：key={}，value={}", entry.getKey(), Arrays.toString(entry.getValue())));
		}
		// 2. 添加跨域支持
		// 如果开头是api 结尾是.json 就可以认为是跨域请求
		if(requestURI.startsWith(BaseUrl.API_PREFIX) && requestURI.endsWith(BaseUrl.API_SUFFIX)){
			response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
			response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
			response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
			// 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
			if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
				response.setStatus(HttpStatus.OK.value());
				return false;
			}
		}
		// 请求时间间隔记录
		log.info("请求开始 请求方式={} , 请求路径={}",request.getMethod(), request.getRequestURI());
		request.setAttribute(REQUEST_TIME, System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.info("HttpInterceptor postHandle" );
		long startTime = (long) request.getAttribute(REQUEST_TIME);
		long endTime = System.currentTimeMillis();
		long interval = endTime - startTime;
		log.info("请求结束  耗时:{}ms ", interval);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.info("HttpInterceptor afterCompletion");
		System.out.println("拦截器 前置处理{HttpInterceptor.afterCompletion}");
		// 异常情况移除请求
		try {
			RequestHolder.remove();
		} catch (Exception e) {
			log.error("异常", e);
		}
	}

}
