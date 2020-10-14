package com.soft1851.springboot.contentcenter.configeration;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbonconfig.RibbonConfiguration;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/9/25
 */
@Configuration
@RibbonClient(name = "user-center",configuration = RibbonConfiguration.class)
//@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class UserCenterRibbonConfig {
}
