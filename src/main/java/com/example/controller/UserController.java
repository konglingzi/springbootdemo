package com.example.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.beans.ItripUser;
import com.example.model.User;
import com.example.service.ItripUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * description:
 * Created by Ray on 2019-08-30
 */
@Api
@Controller
public class UserController {
    @Autowired
    private User user;
    @Autowired
    private ItripUserService itripUserService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);
    @RequestMapping(value = "/showuser",method = RequestMethod.GET)
//    @GetMapping("/showuser")
    @ResponseBody
    public String showUserInfo(){
        return user.toString()+"@@@@@@@@@@";
    }


    @ApiOperation(value ="根据id查询用户",notes = "成功返回json，失败返回null",httpMethod = "GET",protocols = "http")
    @RequestMapping(value = "/showitripuser/{id}",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public String showItripUser(@ApiParam(name = "id",value = "用户id",required = true,example = "29") @PathVariable Long id){
        logger.debug("showitripuser--------{}",id);
        try {
            ItripUser user = itripUserService.getItripUserById(id);
            String jsonUser = new ObjectMapper().writeValueAsString(user);
            return jsonUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
