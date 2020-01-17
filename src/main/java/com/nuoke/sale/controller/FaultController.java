package com.nuoke.sale.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nuoke.sale.model.Fault;
import com.nuoke.sale.model.Fault;
import com.nuoke.sale.service.IFaultService;
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

/**
 * Author:dlkang
 * Date: 2019/11/6
 */
@Controller
@RequestMapping("fault")
public class FaultController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaultController.class);

    @Autowired
    private IFaultService iFaultService;


    @GetMapping("/index")
    public String index() {
        return "fault/list";
    }

    @GetMapping("/add")
    public String add() {
        return "fault/add";
    }

    @PostMapping("/list")
    @ResponseBody
    public LayerData<Fault> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                 ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Fault> userLayerData = new LayerData<>();
        EntityWrapper<Fault> userEntityWrapper = new EntityWrapper<>();
        if (!map.isEmpty()) {
            String keys = (String) map.get("key");
            if (!StringUtils.isEmpty(keys)) {
                userEntityWrapper.like("name", keys);
            }
        }
        Page<Fault> userPage = iFaultService.selectPage(new Page<>(page, limit), userEntityWrapper);
        userLayerData.setCount(userPage.getTotal());
        userLayerData.setData(userPage.getRecords());
        return userLayerData;
    }


    @PostMapping("addSave")
    @ResponseBody
//    @SysLog("保存新增系统用户数据")
    public RestResponse add(@RequestBody Fault fault) {


        if (StringUtils.isEmpty(fault.getId())) {
            if (iFaultService.insert(fault)) {
                return RestResponse.success();
            }
        } else {
            if (iFaultService.updateById(fault)) {
                return RestResponse.success();
            }
        }

        return RestResponse.failure("保存失败!");
    }




    @PostMapping("editSave")
    @ResponseBody
//    @SysLog("保存新增系统用户数据")
    public RestResponse edit(@RequestBody Fault repairMan) {

        if (iFaultService.updateById(repairMan)) {
            return RestResponse.success();
        }

        return RestResponse.failure("添加失败!");
    }
}
