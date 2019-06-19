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
 * @date 2019/5/16 19:00
 **/
@Slf4j
@Component
@Order(1)
@WebFilter(urlPatterns = "/*", filterName = "RequestFilter1")
public class RequestFilter1 implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 这里没有内容
		log.info("RequestFilter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("RequestFilter1 doFilter");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// 这里没有内容
		log.info("RequestFilter1 destroy");
	}
}
