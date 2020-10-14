package com.soft1851.springboot.usercenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/9/23
 */
@RequestMapping(value = "/user")
@RestController
@Slf4j
public class UserHello {
    @GetMapping(value = "/hello")
    public String getHello(){
        log.info("我被调用了。。。");
        return "hello,this message is from user-center";
    }
}
