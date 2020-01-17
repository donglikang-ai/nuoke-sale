package com.nuoke.sale.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nuoke.sale.model.Terminal;
import com.nuoke.sale.service.IRepairService;
import com.nuoke.sale.service.ITerminalService;
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
@RequestMapping("terminal")
public class TerminalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TerminalController.class);

    @Autowired
    private ITerminalService iTerminalService;


    @GetMapping("/index")
    public String index() {
        return "terminal/list";
    }

    @GetMapping("/add")
    public String add() {
        return "terminal/add";
    }

    @PostMapping("/list")
    @ResponseBody
    public LayerData<Terminal> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                    ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Terminal> userLayerData = new LayerData<>();
        EntityWrapper<Terminal> userEntityWrapper = new EntityWrapper<>();
        if (!map.isEmpty()) {
            String keys = (String) map.get("key");
            if (!StringUtils.isEmpty(keys)) {
                userEntityWrapper.like("terminalName", keys);
            }
        }
        Page<Terminal> userPage = iTerminalService.selectPage(new Page<>(page, limit), userEntityWrapper);
        userLayerData.setCount(userPage.getTotal());
        userLayerData.setData(userPage.getRecords());
        return userLayerData;
    }


    @PostMapping("addSave")
    @ResponseBody
//    @SysLog("保存新增系统用户数据")
    public RestResponse add(@RequestBody Terminal terminal) {


        if (StringUtils.isEmpty(terminal.getId())) {
            if (iTerminalService.insert(terminal)) {
                return RestResponse.success();
            }
        } else {
            if (iTerminalService.updateById(terminal)) {
                return RestResponse.success();
            }
        }

        return RestResponse.failure("保存失败!");

    }


    @PostMapping("editSave")
    @ResponseBody
//    @SysLog("保存新增系统用户数据")
    public RestResponse edit(@RequestBody Terminal terminal) {

        if (iTerminalService.updateById(terminal)) {
            return RestResponse.success();
        }

        return RestResponse.failure("添加失败!");
    }
}
