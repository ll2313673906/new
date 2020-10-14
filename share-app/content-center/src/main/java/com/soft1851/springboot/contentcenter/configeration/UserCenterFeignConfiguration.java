package com.soft1851.springboot.contentcenter.configeration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/9/30
 */
public class UserCenterFeignConfiguration {
    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
}
