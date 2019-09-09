package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.ValidatorPoJo;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    /**
     * @Author GuanLS
     * @Description //注解验证测试方法
     * @Date 17:08 2019/9/9
     * @Param [validatorPojo]
     * @return void
     **/
    @RequestMapping("/validate")
    public void validaye(@Valid @RequestBody ValidatorPoJo validatorPojo){
        System.out.println("123");
    }

//                        常用的验证注解
//    @Null  被注释的元素必须为null
//    @NotNull  被注释的元素不能为null
//    @AssertTrue  被注释的元素必须为true
//    @AssertFalse  被注释的元素必须为false
//    @Min(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
//    @Max(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
//    @DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
//    @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
//    @Size(max,min)  被注释的元素的大小必须在指定的范围内。
//    @Digits(integer,fraction)  被注释的元素必须是一个数字，其值必须在可接受的范围内
//    @Past  被注释的元素必须是一个过去的日期
//    @Future  被注释的元素必须是一个将来的日期
//    @Pattern(value) 被注释的元素必须符合指定的正则表达式。
//    @Email 被注释的元素必须是电子邮件地址
//    @Length 被注释的字符串的大小必须在指定的范围内
//    @NotEmpty  被注释的字符串必须非空
//    @Range  被注释的元素必须在合适的范围内

}
