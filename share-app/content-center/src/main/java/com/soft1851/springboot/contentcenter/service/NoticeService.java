package com.soft1851.springboot.contentcenter.service;

import com.soft1851.springboot.contentcenter.domain.entity.Notice;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/10/4
 */
public interface NoticeService {

    Notice getLatest();
}
