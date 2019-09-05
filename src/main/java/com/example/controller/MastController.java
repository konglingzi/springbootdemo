package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description:
 * Created by Ray on 2019-09-05
 */
public class MastController {
    @RequestMapping("/showhello")
    public void show(){
        System.out.println("hello show  master");
        System.out.println("hello show  master123");
        System.out.println("hello show  master456");
    }
    @RequestMapping("/demo")
    public void demo(){
        System.out.println("dmeo 123");
    }
}
