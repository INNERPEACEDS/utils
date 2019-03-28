package com.wgb.utils.exception;

/**
 * @author INNERPEACE
 * @date 2018/12/19 15:25
 **/
public class ParamException extends RuntimeException {

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
