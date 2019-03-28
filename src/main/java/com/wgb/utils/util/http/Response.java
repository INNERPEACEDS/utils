package com.wgb.utils.util.http;

/**
 * @author INNERPEACE
 * @date 2018/12/25 17:27
 **/
public class Response {
    private String message;

    private int httpStatus;

    public Response() {}

    public Response(String message, int httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String toString() {
        return "Response[message=" + message + ", httpStatus=" + httpStatus + "]";
    }
}
