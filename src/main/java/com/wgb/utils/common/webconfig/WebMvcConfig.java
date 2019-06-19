package com.wgb.utils.common.webconfig;

import com.wgb.utils.common.interceptor.HttpInterceptor;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 * @author INNERPEACE
 * @date 2019/5/17 10:13
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


	/**
	 * 添加拦截器
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] addPathPatterns = {"/**"};
		String[] excludePathPatterns = {"/exclude/**"};

		InterceptorRegistration interceptorRegistration = registry.addInterceptor(new HttpInterceptor());
		interceptorRegistration.addPathPatterns(addPathPatterns);
		interceptorRegistration.excludePathPatterns(excludePathPatterns);
	}

	/**
	 * 注册错误页面
	 */
	@Bean
	public ErrorPageRegistrar errorPageRegistrar() {
		return new MyErrorPageRegistrar();
	}

	/**
	 * 设置错误页面
	 */
	private static class MyErrorPageRegistrar  implements ErrorPageRegistrar {
		@Override
		public void registerErrorPages(ErrorPageRegistry registry) {
			registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/notFound.html"));
		}
	}

}
