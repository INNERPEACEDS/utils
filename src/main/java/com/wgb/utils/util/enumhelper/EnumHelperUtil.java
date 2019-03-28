package com.wgb.utils.util.enumhelper;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : innerpeace
 * @date : 2018/11/13 17:40
 */
@Slf4j
public class EnumHelperUtil {

    /**
     * indexOf，传入的参数值ordinal指的是返回的枚举值在设定的枚举类中的顺序，以0开始
     * @param clazz   枚举类
     * @param ordinal 顺序值
     * @param <T>     枚举泛型
     * @return 枚举对象值
     */
    public static <T extends Enum<T>> T indexOf(Class<T> clazz, int ordinal) {
        return clazz.getEnumConstants()[ordinal];
    }

    /**
     * nameOf，传入的参数值name指的是在设定的枚举类中的名称，一般以大写字母加下划线命名
     * @param clazz 枚举类
     * @param name  枚举对象名称
     * @param <T>   枚举泛型
     * @return      枚举对象值
     */
    public static <T extends Enum<T>> T nameOf(Class<T> clazz, String name) {
        return Enum.valueOf(clazz, name);
    }

    /**
     * getEnumByIntegerTypeCode，通过枚举类型中某一变量typeCode(可转换成Number类型)获取枚举对象,其中typeCode为Integer类型
     * @param clazz                 枚举类
     * @param getTypeCodeMethodName typeCode变量的get函数
     * @param typeCode              枚举对象中某个Integer类型变量（例如：0001）
     * @param <T>                   枚举泛型
     * @return                      枚举对象值
     */
    public static <T extends Enum<T>> T getEnumByIntegerTypeCode(Class<T> clazz, String getTypeCodeMethodName, Integer typeCode) {
        T result = null;
        T[] arr = clazz.getEnumConstants();
        log.info("clazz.getEnumConstants()的值为：{}", arr[0]);
        try {
            Method method = clazz.getDeclaredMethod(getTypeCodeMethodName);
            log.info("method:{}", method);
            Integer typeCodeVal = null;
            for (T entity : arr) {
                log.info("entity:{}", entity);
                typeCodeVal = Integer.parseInt(method.invoke(entity).toString());
                //需优化内容，抛出空指针异常
                if (typeCodeVal.equals(typeCode)) {
                    result = entity;
                    break;
                }
            }
        } catch (NoSuchMethodException e) {
            log.info("没有{}方法，请检查", getTypeCodeMethodName);
        } catch (InvocationTargetException e) {
            log.info("执行目标异常");
        } catch (IllegalAccessException e) {
            log.info("不合法的入口异常");
        }
        return result;
    }

    /**
     * getEnumByStringTypeCode，通过枚举类型中某一变量typeCode获取枚举对象,其中typeCode为String类型
     * @param clazz                 枚举类
     * @param getTypeCodeMethodName 变量typeCode的get函数
     * @param typeCode              枚举对象中某一String类型变量（例如："0000"）
     * @param <T>                   枚举泛型
     * @return                      枚举对象
     */
    public static <T extends Enum<T>> T getEnumByStringTypeCode(Class<T> clazz, String getTypeCodeMethodName, String typeCode) {
        return getEnumByStringTypeName(clazz, getTypeCodeMethodName, typeCode);
    }

    /**
     * getEnumByStringTypeName，通过枚举类型中某一变量typeName获取枚举对象,其中typeName为String类型
     * @param clazz                 枚举类
     * @param getTypeNameMethodName 变量typeName的get函数
     * @param typeName              枚举对象中某一String类型变量（例如："成功"）
     * @param <T>                   枚举泛型
     * @return                      枚举对象
     */
    public static <T extends Enum<T>> T getEnumByStringTypeName(Class<T> clazz, String getTypeNameMethodName, String typeName) {
        T result = null;
        try {
            T[] arr = clazz.getEnumConstants();
            Method targetMethod = clazz.getDeclaredMethod(getTypeNameMethodName);
            String typeNameVal = null;
            for (T entity : arr) {
                typeNameVal = targetMethod.invoke(entity).toString();
                if (typeNameVal.contentEquals(typeName)) {
                    result = entity;
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            log.info("不合法的入口异常");
        } catch (IllegalArgumentException e) {
            log.info("不合法的参数异常");
        } catch (InvocationTargetException e) {
            log.info("执行目标异常");
        } catch (NoSuchMethodException e) {
            log.info("没有{}方法，请检查", getTypeNameMethodName);
        }
        return result;
    }
}
