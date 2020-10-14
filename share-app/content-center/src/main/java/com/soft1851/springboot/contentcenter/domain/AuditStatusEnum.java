package com.soft1851.springboot.contentcenter.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/9/29
 */

@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     * 待审核
     */
    NOT_YET,
    /**
     * 审核通过
     */
    PASSED,
    /**
     * 审核不通过
     */
    REJECT
}
