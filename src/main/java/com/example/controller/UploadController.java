package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * description:
 * Created by Ray on 2019-09-05
 */
@Controller
public class UploadController {
    @RequestMapping(value = "/uploadimg",method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(@RequestParam("imgfile") MultipartFile file){
        System.out.println("-----------------------upload");
        String filename = file.getOriginalFilename();
        System.out.println(filename);

        try {
            file.transferTo( new File("D:/upload", filename));
            return  "upload success!";
        } catch (IOException e) {
            e.printStackTrace();
            return "upload fail!";
        }
    }
    @RequestMapping(value = "/test1/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String test1(@PathVariable String id){
        System.out.println("===================="+id);
        return "test success";
    }
}
