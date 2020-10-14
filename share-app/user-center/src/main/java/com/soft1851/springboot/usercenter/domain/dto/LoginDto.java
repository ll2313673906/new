package com.soft1851.springboot.usercenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/10/12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginDto {
    private String openId;
    private String wxNickName;
    private String avatarUrl;
}
