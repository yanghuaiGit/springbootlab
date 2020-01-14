package com.yh.lettue.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * jackson json处理工具
 * Created by lsq on 2018-09-03 17:25
 */
public class JacksonJsonUtil {

    // Jackson ObjectMapper对象
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String object2String(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T string2Object(String jsonString, Class<T> classType) throws IOException {
        return objectMapper.readValue(jsonString, classType);
    }

    public static <T> List<T> string2List(String jsonString, Class<T> valueClassType) throws IOException {
        return objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructParametricType(ArrayList.class, valueClassType));
    }

}
