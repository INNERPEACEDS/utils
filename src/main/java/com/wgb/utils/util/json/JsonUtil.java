package com.wgb.utils.util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * json转换工具
 *
 * @author Alance 2018年08月30日14:18:22
 */
@Slf4j
public class JsonUtil {

    private JsonUtil() {
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // config
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
        // 空值不序列化
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 对象转json
     *
     * @param obj 对象
     * @param <T> 对象类型
     * @return 转换后的json
     */
    public static <T> String toJson(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.warn("parse object to String exception, error:{}", e);
            return null;
        }
    }

    /**
     * json 转对象
     *
     * @param json          字符串对象
     * @param typeReference 对象引用
     * @param <T>           对象类型
     * @return 转换后的对象
     */
    public static <T> T toObject(String json, TypeReference<T> typeReference) {
        if (json == null || typeReference == null) {
            return null;
        }
        try {
            return (T)(String.class.equals(typeReference.getType()) ? json : objectMapper.readValue(json, typeReference));
        } catch (IOException e) {
            log.warn("parse String to Object exception, String:{}, TypeReference<T>:{}, error:{}", json, typeReference.getType(), e);
            return null;
        }
    }


}
