package com.wgb.utils.util.excel.download;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 列开头信息和结尾信息
 */
@Data
@Builder
public class BaseInf {
    /**
     * 标题
     */
    private String titleName;
    /**
     * 行读取方法
     */
    private String columMethod;
    /**
     * 转义列表
     */
    private Map<String, String> map;
    /**
     * 行尾统计 字符串数字都可以
     *
     */
    private String count;
    /**
     * 数据处理类
     */
    private Class<?> clazz;
    /**
     * 数据处理方法
     */
    private Method method;


    /**
     * 全成员构造方法
     */
    public BaseInf(String titleName, String columMethod, Map<String, String> map, String count, Class<?> clazz,
                   Method method) {
        super();
        this.titleName = titleName;
        this.columMethod = columMethod;
        this.map = map;
        this.count = count;
        this.clazz = clazz;
        this.method = method;
    }


    public BaseInf(String titleName, String columMethod, Class<?> clazz, Method method) {
        super();
        this.titleName = titleName;
        this.columMethod = columMethod;
        this.clazz = clazz;
        this.method = method;
    }

    public BaseInf(String titleName, String columMethod, String count, Class<?> clazz, Method method) {
        super();
        this.titleName = titleName;
        this.columMethod = columMethod;
        this.clazz = clazz;
        this.method = method;
        this.count = count;
    }


    /**
     * 有转义列表 有统计的构造方法
     *
     */
    public BaseInf(String titleName, String columMethod, Map<String, String> map, String count) {
        super();
        this.titleName = titleName;
        this.columMethod = columMethod;
        this.map = map;
        this.count = count;
    }

    /**
     * 有转义列表构造方法
     *
     * @param titleName   标题
     * @param columMethod 获取方法
     * @param map         转义列表
     */
    public BaseInf(String titleName, String columMethod, Map<String, String> map) {
        super();
        this.titleName = titleName;
        this.columMethod = columMethod;
        this.map = map;
        this.count = null;
    }

    /**
     * 无转义列表 有合计数据
     *
     * @param titleName   标题
     * @param columMethod 读取方法列表
     * @param count       合计值
     */
    public BaseInf(String titleName, String columMethod, String count) {
        super();
        this.titleName = titleName;
        this.columMethod = columMethod;
        this.map = null;
        this.count = count;
    }

    /**
     * 无转义列表无统计构造方法
     *
     * @param titleName   标题
     * @param columMethod 获取方法
     */
    public BaseInf(String titleName, String columMethod) {
        super();
        this.titleName = titleName;
        this.columMethod = columMethod;
        this.map = null;
        this.count = null;
    }
}
