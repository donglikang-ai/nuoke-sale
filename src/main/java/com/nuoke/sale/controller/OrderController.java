package com.nuoke.sale.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nuoke.sale.model.Fault;
import com.nuoke.sale.model.Order;
import com.nuoke.sale.model.RepairMan;
import com.nuoke.sale.service.IFaultService;
import com.nuoke.sale.service.IOrderService;
import com.nuoke.sale.service.IRepairService;
import com.nuoke.sale.util.LayerData;
import com.nuoke.sale.util.RestResponse;
import com.nuoke.sale.util.weix.TemplateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
    @Autowired
    private IRepairService iRepairService;


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
        userEntityWrapper.orderAsc(Arrays.asList(new String[]{"status", "createDate"}));
        Page<Order> userPage = iOrderService.selectPage(new Page<>(page, limit), userEntityWrapper);
        userLayerData.setCount(userPage.getTotal());
        userLayerData.setData(userPage.getRecords());
        return userLayerData;
    }


    @PostMapping("addSave")
    @ResponseBody
    public RestResponse add(@RequestBody Order order) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setCreateDate(simpleDateFormat.format(new Date()));
        if (iOrderService.insert(order)) {
            new Thread(() -> TemplateUtils.templateSend(order)).start();
            return RestResponse.success();
        }

        return RestResponse.failure("下单失败！");
    }


    @PostMapping("close")
    @ResponseBody
    public RestResponse close(@RequestParam(value = "id", required = false) String id) {


        if (id == null) {
            return RestResponse.failure("参数错误");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Order entity = iOrderService.selectById(id);
        entity.setStatus(2);
        entity.setCloseDate(sdf.format(new Date()));

        iOrderService.updateById(entity);
        Order order = iOrderService.selectById(id);
        new Thread(() -> TemplateUtils.templateSend(order)).start();
        return RestResponse.success();
    }

    @PostMapping("assign")
    @ResponseBody
    public RestResponse assignOrder(@RequestBody Order order) {

        Order entity = iOrderService.selectById(order.getId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        RepairMan man = iRepairService.selectById(order.getRepairmanId());
        entity.setDoDate(sdf.format(new Date()));
        entity.setRepairmanName(order.getRepairmanName());
        entity.setRepairmanName(man.getName());
        entity.setRepairmanTel(man.getMobile());
        entity.setStatus(1);

        iOrderService.updateById(entity);

        new Thread(() -> TemplateUtils.templateSend(entity)).start();
        return RestResponse.success();
    }
}
