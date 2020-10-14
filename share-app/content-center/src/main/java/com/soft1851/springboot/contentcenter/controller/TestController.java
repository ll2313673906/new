package com.soft1851.springboot.contentcenter.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.soft1851.springboot.contentcenter.domain.dto.UserDto;
import com.soft1851.springboot.contentcenter.figinclient.TestUserCenterFeignClient;
import com.soft1851.springboot.contentcenter.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/9/23
 */
@Slf4j
@RestController
@RequestMapping(value = "test")
@ApiIgnore
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TestUserCenterFeignClient testUserCenterFeignClient;
    @Autowired
    private TestService testService;
//    @Autowired
//    private MD5Service md5Service;

    @GetMapping("/discovery")
    public List<ServiceInstance> getInstances(){
        //查询指定服务的所有实例信息
        return this.discoveryClient.getInstances("user-center");
    }

    @GetMapping(value = "/call/hello")
    public String callUserCenter(){
        List<ServiceInstance> instances = this.discoveryClient.getInstances("user-center");
//        String targetUrl = instances.stream()
//                .map(instance -> instance.getUri().toString()+"/user/hello")
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("当前没有示例"));

        ServiceInstance ServiceInstance = instances.get(new Random().nextInt(instances.size()));
        String targetUrl = ServiceInstance.getUri()+"/user/hello";
        log.info("请求目标地址:{}"+targetUrl);
        return restTemplate.getForObject(targetUrl,String.class);
    }

    @GetMapping(value = "/call/ribbon")
    public String callByRibbon(){
        return restTemplate.getForObject("http://user-center/user/hello",String.class);
    }

    @GetMapping(value = "/test-q")
    public UserDto query(UserDto userDto){
        return testUserCenterFeignClient.query(userDto);
    }

    @GetMapping("test-a")
    public String testA() {
        this.testService.commonMethod();
        return "test-a";
    }

    @GetMapping("test-b")
    public String testB() {
        this.testService.commonMethod();
        return "test-b";
    }

    @GetMapping("byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public String byResource(){
        return "按名称限流";
    }

    public String handleException(BlockException blockException){
        return "服务不可用";
    }
//
//    @RequestMapping("/getMD5")
//    public String getMD5() {
//        return "MD5加密结果为：" + md5Service.getMD5("mypassword");
//    }
}

