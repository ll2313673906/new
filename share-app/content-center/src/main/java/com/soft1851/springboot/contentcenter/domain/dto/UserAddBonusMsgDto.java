package com.soft1851.springboot.contentcenter.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/10/8
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserAddBonusMsgDto {
    private Integer userId;

    private Integer bonus;
}
