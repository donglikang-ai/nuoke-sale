package com.nuoke.sale.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nuoke.sale.model.Notice;
import com.nuoke.sale.service.IFaultService;
import com.nuoke.sale.service.INoticeService;
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
@RequestMapping("notice")
public class NoticeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private INoticeService iNoticeService;


    @GetMapping("/index")
    public String index() {
        return "notice/list";
    }

    @GetMapping("/add")
    public String add() {
        return "notice/add";
    }

    @PostMapping("/list")
    @ResponseBody
    public LayerData<Notice> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                  ServletRequest request) {
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<Notice> userLayerData = new LayerData<>();
        EntityWrapper<Notice> userEntityWrapper = new EntityWrapper<>();
        if (!map.isEmpty()) {
            String keys = (String) map.get("key");
            if (!StringUtils.isEmpty(keys)) {
                userEntityWrapper.like("name", keys);
            }
        }
        Page<Notice> userPage = iNoticeService.selectPage(new Page<>(page, limit), userEntityWrapper);
        userLayerData.setCount(userPage.getTotal());
        userLayerData.setData(userPage.getRecords());
        return userLayerData;
    }


    @PostMapping("addSave")
    @ResponseBody
//    @SysLog("保存新增系统用户数据")
    public RestResponse add(@RequestBody Notice notice) {

        if (StringUtils.isEmpty(notice.getId())) {
            if (iNoticeService.insert(notice)) {
                return RestResponse.success();
            }
        } else {
            if (iNoticeService.updateById(notice)) {
                return RestResponse.success();
            }
        }

        return RestResponse.failure("保存失败!");
    }




    @PostMapping("del")
    @ResponseBody
//    @SysLog("保存新增系统用户数据")
    public RestResponse delete(@RequestParam(value = "id", required = false) String id) {


        if (id == null) {
            return RestResponse.failure("参数错误");
        }

        if (iNoticeService.deleteById(id)) {
            return RestResponse.success();
        }

        return RestResponse.failure("删除失败!");
    }
}
