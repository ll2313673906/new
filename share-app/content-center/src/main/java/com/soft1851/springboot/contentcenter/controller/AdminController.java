package com.soft1851.springboot.contentcenter.controller;

import com.soft1851.springboot.contentcenter.domain.dto.AuditStatusDto;
import com.soft1851.springboot.contentcenter.domain.entity.Share;
import com.soft1851.springboot.contentcenter.service.ShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/10/8
 */
@RestController
@RequestMapping(value = "/admin")
@Api(tags = "管理员接口",value = "提供管理员相关的Rest API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final ShareService shareService;

    @PutMapping(value = "/shares/audit/{id}")
    @ApiOperation(value = "修改状态", notes = "修改状态")
    public Share updateAuditStatus(@PathVariable Integer id, @RequestBody AuditStatusDto auditStatusDto){
         return this.shareService.auditStatusById(id,auditStatusDto);
    }
}
