package com.soft1851.springboot.contentcenter.service.impl;

import com.soft1851.springboot.contentcenter.domain.entity.Notice;
import com.soft1851.springboot.contentcenter.mapper.NoticeMapper;
import com.soft1851.springboot.contentcenter.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/10/4
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeServiceImpl implements NoticeService {
    public final NoticeMapper noticeMapper;

    @Override
    public Notice getLatest() {
        Example example = new Example(Notice.class);
        //按id降序
        example.setOrderByClause("id DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("showFlag",1);
        return noticeMapper.selectByExample(example).get(0);
    }
}

