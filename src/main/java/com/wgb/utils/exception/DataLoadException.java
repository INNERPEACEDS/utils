package com.wgb.utils.exception;

/**
 * @author INNERPEACE
 * @date 2020/5/6 15:24
 */
public class DataLoadException extends RuntimeException {
	public DataLoadException(String message) {
		super(message);
	}

	public DataLoadException(String message, Throwable cause) {
		super(message, cause);
	}
}
