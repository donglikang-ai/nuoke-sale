package com.nuoke.sale.controller;

import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.nuoke.sale.util.RestResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Objects;

/**
 * Author:dlkang
 * Date: 2019/11/5
 */
@Controller
public class LoginController {

    @RequestMapping("login/main")
    @ResponseBody
    public RestResponse login(String userName, String password) {

        if (!Objects.equals(userName, "nuoke") || !Objects.equals(password, "nuoke123")) {
            return RestResponse.failure("用户名或密码错误");
        }

        Map<String, Object> map = Maps.newHashMap();
        map.put("url", "index");
        return RestResponse.success("登录成功").setData(map);
    }
}
