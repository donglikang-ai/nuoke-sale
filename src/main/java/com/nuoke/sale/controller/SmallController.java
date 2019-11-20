package com.nuoke.sale.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.nuoke.sale.model.*;
import com.nuoke.sale.service.IFaultService;
import com.nuoke.sale.service.INoticeService;
import com.nuoke.sale.service.IOrderService;
import com.nuoke.sale.service.ITerminalService;
import com.nuoke.sale.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                .eq("status", 2));
        Map<String, Object> map = Maps.newHashMap();
        map.put("orders", Objects.isNull(orders) ? null : orders);


        return RestResponse.success().setData(map);
    }


    @GetMapping("faults")
    public RestResponse getFaults() {

        List<Terminal> terminals = iTerminalService.selectList(new EntityWrapper<>());
        List<Fault> faults = iFaultService.selectList(new EntityWrapper<>());

        Map<String, Object> map = Maps.newHashMap();
        map.put("terminals", Objects.isNull(terminals) ? null : terminals.stream().map(i -> i.getTerminalName()).collect(Collectors.toList()));
        map.put("faults", Objects.isNull(faults) ? null : faults.stream().map(i -> i.getFaultName()).collect(Collectors.toList()));


        return RestResponse.success().setData(map);
    }
}
