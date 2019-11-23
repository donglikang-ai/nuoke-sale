package com.nuoke.sale.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nuoke.sale.model.RepairMan;
import com.nuoke.sale.service.IRepairService;
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
import java.util.Map;
import java.util.Objects;

/**
 * Author:dlkang
 * Date: 2019/11/6
 */
@Controller
@RequestMapping("repairman")
public class RepairManController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepairManController.class);

    @Autowired
    private IRepairService iRepairService;


    @GetMapping("/index")
    public String index() {
        return "repairman/list";
    }

    @GetMapping("/add")
    public String add() {
        return "repairman/add";
    }

    @PostMapping("/list")
    @ResponseBody
    public LayerData<RepairMan> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                     ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<RepairMan> userLayerData = new LayerData<>();
        EntityWrapper<RepairMan> userEntityWrapper = new EntityWrapper<>();
        if (!map.isEmpty()) {
            String keys = (String) map.get("key");
            if (!StringUtils.isEmpty(keys)) {
                userEntityWrapper.like("name", keys);
            }
        }
        Page<RepairMan> userPage = iRepairService.selectPage(new Page<>(page, limit), userEntityWrapper);
        userLayerData.setCount(userPage.getTotal());
        userLayerData.setData(userPage.getRecords());
        return userLayerData;
    }


    @PostMapping("addSave")
    @ResponseBody
    public RestResponse add(@RequestBody RepairMan repairMan) {

        if (StringUtils.isEmpty(repairMan.getId())) {
            if (iRepairService.insert(repairMan)) {
                return RestResponse.success();
            }
        } else {
            if (iRepairService.updateById(repairMan)) {
                return RestResponse.success();
            }
        }


        return RestResponse.failure("保存失败!");
    }


    @PostMapping("editSave")
    @ResponseBody
    public RestResponse edit(@RequestBody RepairMan repairMan) {

        if (iRepairService.updateById(repairMan)) {
            return RestResponse.success();
        }

        return RestResponse.failure("添加失败!");
    }


    @GetMapping("mansList")
    @ResponseBody
    public RestResponse mansList() {


        return RestResponse.success().setData(iRepairService.selectList(new EntityWrapper<>()));
    }
}
