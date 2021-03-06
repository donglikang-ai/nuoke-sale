package com.nuoke.sale.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import com.nuoke.sale.model.*;
import com.nuoke.sale.service.IFaultService;
import com.nuoke.sale.service.INoticeService;
import com.nuoke.sale.service.IOrderService;
import com.nuoke.sale.service.ITerminalService;
import com.nuoke.sale.util.JsonParse;
import com.nuoke.sale.util.RestResponse;
import com.nuoke.sale.util.weix.WeixConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Author:dlkang
 * Date: 2019/11/20
 */
@RequestMapping("small")
@RestController
public class SmallController {

    @Autowired
    private INoticeService iNoticeService;
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private ITerminalService iTerminalService;
    @Autowired
    private IFaultService iFaultService;


    @PostMapping("getUserInfo")
    public RestResponse getUserInfo(UserinfoGet userinfoGet) throws Exception {

//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
//                + WeixConst.AppID
//                + "&secret="
//                + WeixConst.AppSecret
//                + "&js_code="
//                + userinfoGet.getCode() + "&grant_type=authorization_code";

        String url = String.format(WeixConst.UserInfoUrl, WeixConst.AppID, WeixConst.AppSecret, userinfoGet.getCode());

        URL reqURL = new URL(url);
        HttpsURLConnection openConnection = (HttpsURLConnection) reqURL
                .openConnection();
        openConnection.setConnectTimeout(10000);
        //这里我感觉获取openid的时间比较长，不过也可能是我网络的问题，
        //所以设置的响应时间比较长
        openConnection.connect();
        InputStream in = openConnection.getInputStream();

        StringBuilder builder = new StringBuilder();
        BufferedReader bufreader = new BufferedReader(new InputStreamReader(in));
        for (String temp = bufreader.readLine(); temp != null; temp = bufreader
                .readLine()) {
            builder.append(temp);
        }

        in.close();
        openConnection.disconnect();
        return RestResponse.success().setData(JsonParse.parseObject(builder.toString(), HashMap.class));
    }


    @PostMapping("info")
    public RestResponse getBase(@RequestBody BaseGet baseGet) {

        List<Notice> noticeList = iNoticeService.selectList(new EntityWrapper<>());
        List<Order> orders = iOrderService.selectList(new EntityWrapper<Order>()
                .eq("openid", baseGet.getOpenid())
                .ne("status", 2));
        Map<String, Object> map = Maps.newHashMap();
        map.put("notice", Objects.isNull(noticeList) ? "" : Joiner.on("|").join(noticeList.stream().map(o -> o.getNotice()).collect(Collectors.toList())));
        map.put("orders", Objects.isNull(orders) ? null : orders);


        return RestResponse.success().setData(map);
    }

    @PostMapping("orders")
    public RestResponse getOrders(@RequestBody BaseGet baseGet) {

        List<Order> orders = iOrderService.selectList(new EntityWrapper<Order>()
                .eq("openid", baseGet.getOpenid())
                .eq("status", 2)
                .orderBy("closeDate", false));
        Map<String, Object> map = Maps.newHashMap();
        map.put("orders", Objects.isNull(orders) ? null : orders);


        return RestResponse.success().setData(map);
    }


    @PostMapping("orderInfo")
    public RestResponse getOrders(@RequestBody OrderGet orderGet) {

        Order order = iOrderService.selectById(orderGet.getId());

        return RestResponse.success().setData(order);
    }

    @GetMapping("faults")
    public RestResponse getFaults() {

        List<Terminal> terminals = iTerminalService.selectList(new EntityWrapper<Terminal>().orderBy("terminal_name"));
        List<Fault> faults = iFaultService.selectList(new EntityWrapper<Fault>().orderBy("fault_name"));

        Map<String, Object> map = Maps.newHashMap();
        map.put("terminals", Objects.isNull(terminals) ? null : terminals.stream().map(i -> i.getTerminalName()).collect(Collectors.toList()));
        map.put("faults", Objects.isNull(faults) ? null : faults.stream().map(i -> i.getFaultName()).collect(Collectors.toList()));


        return RestResponse.success().setData(map);
    }

    @GetMapping("faultCheck")
    public RestResponse getFaultsCheck() {

        List<Fault> faults = iFaultService.selectList(new EntityWrapper<Fault>().orderBy("fault_name"));

        Map<String, Object> map = Maps.newHashMap();
        map.put("checks", faults);


        return RestResponse.success().setData(map);
    }

    @RequestMapping(value = "/pic", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage() throws IOException {
        File file = new File("nuoke_img/nuoke_pic1.png");
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

}
