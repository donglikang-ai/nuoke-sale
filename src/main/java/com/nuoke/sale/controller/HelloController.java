package com.nuoke.sale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:dlkang
 * Date: 2019/11/4
 */
@RequestMapping("")
@Controller
public class HelloController {

    @RequestMapping("")
    public String hello(){
        return "login";
    }


    @GetMapping("index")
    public String index(Model model){
        return "index";
    }


    @GetMapping("main")
    public String main(Model model){
        return "main";
    }
}
