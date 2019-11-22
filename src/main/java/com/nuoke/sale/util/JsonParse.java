package com.nuoke.sale.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParse {

    /**
     * json转对象
     *
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, Class<T> tClass) {
        Gson gson = new GsonBuilder().serializeNulls().create();

        return gson.fromJson(json, tClass);
    }

    /**
     * 对象转json串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        Gson gson = new GsonBuilder().serializeNulls().create();

        return gson.toJson(object);
    }
}
