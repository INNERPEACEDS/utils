package com.wgb.utils.common.filter;

import com.wgb.utils.common.request.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author INNERPEACE
 * @date 2019/5/16 18:52
 **/
@Slf4j
@Component
@Order(0)
@WebFilter(urlPatterns = "/*", filterName = "RequestFilter")
public class RequestFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 这里没有内容
		log.info("RequestFilter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("RequestFilter doFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		RequestHolder.add(req);
		chain.doFilter(request, response);
		RequestHolder.remove();
	}

	@Override
	public void destroy() {
		// 这里没有内容
		log.info("RequestFilter destroy");
	}
}
