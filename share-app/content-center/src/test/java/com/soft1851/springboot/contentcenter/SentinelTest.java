package com.soft1851.springboot.contentcenter;

import org.springframework.web.client.RestTemplate;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/10/6
 */
public class SentinelTest {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0;i < 100;i++){
                String object = restTemplate.getForObject("http://localhost:8081/test/byResource",String.class);
            System.out.println(object);
 //           Thread.sleep(1000);
        }
    }
}
