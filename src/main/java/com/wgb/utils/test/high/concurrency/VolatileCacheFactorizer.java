package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 清单3.13 使用到不可变容器对象的volatile类型引用，缓存最新的结果
 * @author INNERPEACE
 * @date 2020/4/24 11:41
 */
@ThreadSafe
public class VolatileCacheFactorizer implements Servlet {
	private volatile OneValueCache cache = new OneValueCache(null,null);

	public static void main(String[] args) {

	}

	public void getFactors(BigInteger value) {
		BigInteger i = value;
		BigInteger[] factors = cache.getFactors(i);
		if (factors == null) {
			// factors = factor(i);
			cache = new OneValueCache(i, factors);
		}
//		encodeIntoResponse(resp, factors);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		/*BigInteger i = extractFormRequest(req);
		BigInteger[] factors = cache
				.getFactors(i);
		if (factors == null) {
			factors = factor(i);
			cache = new OneValueCache(i, factors);
		}
		encodeIntoResponse(resp, factors);*/
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {

	}
}
