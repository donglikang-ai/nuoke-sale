package com.nuoke.sale.util.weix;

import com.nuoke.sale.util.HttpUtils;
import com.nuoke.sale.util.JsonParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class TokenGet {

    private static Logger log = LoggerFactory.getLogger(TokenGet.class);

    public static String token;


    public static String get() {

        String url = String.format(WeixConst.TokenUrl, WeixConst.AppID, WeixConst.AppSecret);
        String result = HttpUtils.doGet(url);

        log.info("token获取返回结果,{}", result);

        Map<String, Object> resMap = new HashMap<>();
        resMap = JsonParse.parseObject(result, Map.class);

        System.out.println(resMap.get("access_token") == null ? "" : resMap.get("access_token").toString());
        return resMap.get("access_token") == null ? "" : resMap.get("access_token").toString();
    }

    public static void main(String[] args) {
        get();
    }

}
