package com.nuoke.sale.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nuoke.sale.model.Fault;
import com.nuoke.sale.model.Order;
import com.nuoke.sale.service.IFaultService;
import com.nuoke.sale.service.IOrderService;
import com.nuoke.sale.util.LayerData;
import com.nuoke.sale.util.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Author:dlkang
 * Date: 2019/11/6
 */
@Controller
@RequestMapping("order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private IOrderService iOrderService;


    @GetMapping("/index")
    public String index() {
        return "order/list";
    }

    @GetMapping("/add")
    public String add() {
        return "order/add";
    }


    @PostMapping("/list")
    @ResponseBody
    public LayerData<Order> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                 ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Order> userLayerData = new LayerData<>();
        EntityWrapper<Order> userEntityWrapper = new EntityWrapper<>();
        if (!map.isEmpty()) {
            String keys = (String) map.get("key");
            if (!StringUtils.isEmpty(keys)) {
                userEntityWrapper.like("status", keys);
            }
        }
        Page<Order> userPage = iOrderService.selectPage(new Page<>(page, limit), userEntityWrapper);
        userLayerData.setCount(userPage.getTotal());
        userLayerData.setData(userPage.getRecords());
        return userLayerData;
    }


    @PostMapping("addSave")
    @ResponseBody
//    @SysLog("保存新增系统用户数据")
    public RestResponse add(@RequestBody Order order) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setCreateDate(simpleDateFormat.format(new Date()));
        if (iOrderService.insert(order)) {
            return RestResponse.success();
        }

        return RestResponse.failure("保存成功！");
    }


    @PostMapping("close")
    @ResponseBody
    public RestResponse close(@RequestParam(value = "id", required = false) String id) {


        if (id == null) {
            return RestResponse.failure("参数错误");
        }

        if (iOrderService.updateForSet("status=2", new EntityWrapper<Order>().eq("id", id))) {
            return RestResponse.success();
        }

        return RestResponse.failure("关闭失败!");
    }
}
