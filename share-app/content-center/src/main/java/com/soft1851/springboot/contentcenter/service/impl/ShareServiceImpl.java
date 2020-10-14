package com.soft1851.springboot.contentcenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft1851.springboot.contentcenter.domain.AuditStatusEnum;
import com.soft1851.springboot.contentcenter.domain.dto.*;
import com.soft1851.springboot.contentcenter.domain.entity.MidUserShare;
import com.soft1851.springboot.contentcenter.domain.entity.Share;
import com.soft1851.springboot.contentcenter.figinclient.UserCenterFeignClient;
import com.soft1851.springboot.contentcenter.mapper.MidUserShareMapper;
import com.soft1851.springboot.contentcenter.mapper.ShareMapper;
import com.soft1851.springboot.contentcenter.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/9/29
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {

    private final ShareMapper shareMapper;
    private final UserCenterFeignClient userCenterFeignClient;
    private final MidUserShareMapper midUserShareMapper;
    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public ShareDto findById(Integer id) {
// 获   //取分享实体
        Share share = this.shareMapper.selectByPrimaryKey(id);
        // 获得发布人id
        Integer userId = share.getUserId();

        // 1. 代码不可读
        // 2. 复杂的url难以维护：https://user-center/s?ie={ie}&f={f}&rsv_bp=1&rsv_idx=1&tn=baidu&wd=a&rsv_pq=c86459bd002cfbaa&rsv_t=edb19hb%2BvO%2BTySu8dtmbl%2F9dCK%2FIgdyUX%2BxuFYuE0G08aHH5FkeP3n3BXxw&rqlang=cn&rsv_enter=1&rsv_sug3=1&rsv_sug2=0&inputT=611&rsv_sug4=611
        // 3. 难以相应需求的变化，变化很没有幸福感
        // 4. 编程体验不统一
        UserDto userDto = this.userCenterFeignClient.findUserById(userId);

        ShareDto shareDto = new ShareDto();
        // 属性的装配
        BeanUtils.copyProperties(share,shareDto);
        shareDto.setUserDto(userDto);
        return shareDto;

    }

    @Override
    public PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId) {
        //启动分页
        PageHelper.startPage(pageNo,pageSize);
        //构造查询实例
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        //如果标题关键字不空，则加上模糊查询条件，否则结果即所有数据
        if(StringUtil.isNotEmpty(title)){
            criteria.andLike("title","%"+title+"%");
        }
        //处理按条件查询
        List<Share> shares = this.shareMapper.selectByExample(example);
        //处理后的share数据列表
        List<Share> sharesDeal;
        //1.如果用户未登陆，那么downloadUrl全部为null
        if(userId == null){
            sharesDeal = shares.stream()
                    .peek(share -> {
                        share.setDownloadUrl(null);
                    })
                    .collect(Collectors.toList());
        }
        //2.如果用户登陆了
        else {
            sharesDeal = shares.stream()
                    .peek(share -> {
                        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                                MidUserShare.builder()
                                .shareId(share.getId())
                                .userId(userId)
                                .build()
                        );
                        if (midUserShare == null){
                            share.setDownloadUrl(null);
                        }
                    })
                    .collect(Collectors.toList());

        }
        return new PageInfo<>(sharesDeal);
    }

    @Override
    public Share contributeShare(ContributeShareDto contributeShareDto) {
        Share share = Share.builder()
                .userId(contributeShareDto.getUserId())
                .title(contributeShareDto.getTitle())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .isOriginal(contributeShareDto.getIsOriginal())
                .author(contributeShareDto.getAuthor())
                .cover(contributeShareDto.getCover())
                .summary(contributeShareDto.getSummary())
                .price(contributeShareDto.getPrice())
                .downloadUrl(contributeShareDto.getDownloadUrl())
                .buyCount(0)
                .showFlag(false)
                .auditStatus("NOT_YET")
                .reason(contributeShareDto.getReason())
                .build();
        shareMapper.insert(share);
        return share;
    }

    @Override
    public Share auditStatusById(Integer id,AuditStatusDto auditStatusDto) {
        //1、查询share是否存在，不存在或者当前aduit_status ！= NOT_YET,那么抛异常
        Share share = this.shareMapper.selectByPrimaryKey(id);
        if (share == null){
            throw new  IllegalArgumentException("参数非法！该分享不存在");
        }
        if(!Objects.equals("NOT_YET",share.getAuditStatus())){
            throw new  IllegalArgumentException("参数非法！该分享已审核通过或者审核不通过！");
        }

        //2、审核资源，将状态改为PASS或者REJECT
        //这个API主要流程是审核，所以不需要等更新积分的结果返回，可以将加积分改为异步
        share.setReason(auditStatusDto.getReason());
        share.setAuditStatus(auditStatusDto.getAuditStatusEnum().toString());
        this.shareMapper.updateByPrimaryKey(share);

        //3如果是PASS，那么发送消息给rocketmq。让用户中心去消费，并为发布人添加积分
//        if(AuditStatusEnum.PASSED.equals(auditStatusDto.getAuditStatusEnum())){
//            this.rocketMQTemplate.convertAndSend(
//                    "add-bonus",
//                    UserAddBonusMsgDto.builder()
//                    .userId(share.getUserId())
//                    .bonus(50)
//                    .build()
//            );
//        }

        //通过feign调用
        if(AuditStatusEnum.PASSED.equals(auditStatusDto.getAuditStatusEnum())){
            UserAddBonusMsgDto userAddBonusMsgDto = UserAddBonusMsgDto.builder()
                    .userId(share.getUserId())
                    .bonus(50)
                    .build();
            userCenterFeignClient.addBonusById(userAddBonusMsgDto);
        }
        return share;
    }


}
