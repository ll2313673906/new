package com.soft1851.springboot.contentcenter.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.soft1851.springboot.contentcenter.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/10/6
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService{
    //指定sentinel的资源名称
    @Override
    @SentinelResource("common")

    public String commonMethod() {
        log.info("commonMethod....");
        return "common";
    }
}
