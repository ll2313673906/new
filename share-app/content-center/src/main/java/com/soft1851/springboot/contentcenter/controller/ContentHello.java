package com.soft1851.springboot.contentcenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/9/23
 */
@RestController
@RequestMapping(value = "/content")
@ApiIgnore
public class ContentHello {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/call")
    public String getHello(){
        return restTemplate.getForObject("http://localhost:8083/user/hello",String.class);
    }
}
