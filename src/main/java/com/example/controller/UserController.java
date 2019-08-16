package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testBoot")
public class UserController {
 
    @Autowired
    private UserService userService;
    

    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id){
        return userService.Sel(id).toString();
    }

    @RequestMapping("testJson")
    public String TestJson(@RequestBody String  json){
        JSONObject jsonObject = JSONObject.parseObject(json);
        String temp = jsonObject.getString("detail");
        return temp;
    }
}
