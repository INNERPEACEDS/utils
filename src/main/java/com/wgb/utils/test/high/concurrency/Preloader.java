package com.wgb.utils.test.high.concurrency;

import com.wgb.utils.exception.DataLoadException;
import net.sf.ehcache.util.ProductInfo;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 清单5.12 使用FutureTask预载稍后需要的数据
 * @author INNERPEACE
 * @date 2020/5/6 15:17
 */
public class Preloader {
	private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
		@Override
		public ProductInfo call() throws Exception {
			return loadProductInfo();
		}

		private ProductInfo loadProductInfo() {
			ProductInfo productInfo = new ProductInfo();

			return new ProductInfo();
		}
	});

	private final Thread thread = new Thread(future);

	public void start() {
		thread.start();
	}

	public ProductInfo get() throws InterruptedException, Error {
		try {
			return future.get();
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			if (cause instanceof DataLoadException) {
				throw (DataLoadException) cause;
			} else {
				throw launderThrowable(cause);
			}
		}
	}

	public static RuntimeException launderThrowable(Throwable t) {
		if (t instanceof RuntimeException) {
			return (RuntimeException) t;
		} else if (t instanceof Error){
			throw (Error) t;
		} else {
			throw new IllegalStateException("Not unchecked", t);
		}
	}
}
