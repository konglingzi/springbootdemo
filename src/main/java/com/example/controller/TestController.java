package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description:
 * Created by Ray on 2019-09-03
 */
@Controller
public class TestController {
    @RequestMapping("/index")
//    @ResponseBody
    public String showIndex(){
        return "index";
    }
    @RequestMapping("/testtemp")
    public String testTemplate(Model model){
        System.out.println("-----------------testTemp");
        model.addAttribute("name","zhansan");
        return  "template";
    }
}
