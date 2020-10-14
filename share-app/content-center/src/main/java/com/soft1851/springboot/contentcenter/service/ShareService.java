package com.soft1851.springboot.contentcenter.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.springboot.contentcenter.domain.dto.AuditStatusDto;
import com.soft1851.springboot.contentcenter.domain.dto.ContributeShareDto;
import com.soft1851.springboot.contentcenter.domain.dto.ShareDto;
import com.soft1851.springboot.contentcenter.domain.entity.Share;
import com.soft1851.springboot.contentcenter.mapper.ShareMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/9/28
 */

public interface ShareService {

    /**
     * 获得分享详情
     * @param id
     * @return
     */
    ShareDto findById(Integer id);

    /**
     * 根据用户查询分享列表
     * @param title
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    PageInfo<Share> query(String title,Integer pageNo,Integer pageSize,Integer userId);


    /**
     * 投稿
     * @param contributeShareDto
     * @return
     */
    Share contributeShare(ContributeShareDto contributeShareDto);

    /**
     *
     * 更新状态
     * @param id
     * @param auditStatusDto
     * @return
     */
    Share auditStatusById(Integer id,AuditStatusDto auditStatusDto);
}
