package com.wgb.utils.entity.result;

import lombok.Getter;

/**
 * @author : innerpeace
 * @date : 2018/11/14 15:36
 */
@Getter
public enum  ResultEnum {
    /**
     * 成功消息
     */
    SUCCESS(0000, "0000", "成功"),

    /**
     * 失败消息
     */
    FAIL(0100, "0100", "失败"),

    /**
     * 异常消息
     */
    EXCEPTION(0200,"0200","异常"),

    /**
     * 存储异常
     */
    EXCEPTION_SAVE(0201, "0201", "存储数据发生异常"),

    /**
     * 上传失败
     */
    UPLOAD_EXCEPTION(0220, "0220", "上传异常"),

    /**
     * 上传类型不匹配
     */
    UPLOAD_TYPE_EXCEPTION(0221, "0221", "上传类型不匹配");

    /**
     * 序号（从100开始）
     */
    private Integer ordinal;
    /**
     * 代码
     */
    private String code;

    /**
     * 消息
     */
    private String msg;

    ResultEnum(Integer ordinal, String code, String msg) {
        this.ordinal = ordinal;
        this.code = code;
        this.msg = msg;
    }

}
