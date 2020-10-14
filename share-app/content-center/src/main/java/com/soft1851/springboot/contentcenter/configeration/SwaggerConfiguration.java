package com.soft1851.springboot.contentcenter.configeration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/10/4
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * swagger 信息
     * @return 页面信息
     */
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "share-app Swagger接口文档",
                "github地址：https://github.com/Kuzma77",
                "API V1.0",
                "Terms of service",
                new Contact("裤子马","","26733272666@qq,com"),
                "Apache","http://www.apache.org/", Collections.emptyList()
        );
    }

    /**
     * Docket配置
     * @return
     */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.soft1851.springboot.contentcenter"))
                .build()
                .apiInfo(apiInfo());
    }
}
