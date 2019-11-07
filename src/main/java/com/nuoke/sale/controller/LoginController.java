package com.nuoke.sale.controller;

import com.google.common.collect.Maps;
import com.nuoke.sale.util.RestResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Author:dlkang
 * Date: 2019/11/5
 */
@Controller
public class LoginController {

    @RequestMapping("login/main")
    @ResponseBody
    public RestResponse login() {

        Map<String,Object> map = Maps.newHashMap();
        map.put("url", "index");
        return RestResponse.success("登录成功").setData(map);
    }
}
