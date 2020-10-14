package com.soft1851.springboot.usercenter.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/10/13
 */
@Builder
@Data
public class UserRespDto {
    private Integer id;
    private String avatarUrl;
    private Integer bonus;
    private String wxNickName;
}
