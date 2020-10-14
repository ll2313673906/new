package com.soft1851.springboot.contentcenter.domain.dto;

import com.soft1851.springboot.contentcenter.domain.AuditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/10/7
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuditStatusDto {

    private AuditStatusEnum auditStatusEnum;

    private String reason;

}
