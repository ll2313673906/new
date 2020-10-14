package com.soft1851.springboot.usercenter.rocketMQListener;

import com.soft1851.springboot.usercenter.domain.dto.UserAddBonusMsgDto;
import com.soft1851.springboot.usercenter.domain.entity.BonusEventLog;
import com.soft1851.springboot.usercenter.domain.entity.User;
import com.soft1851.springboot.usercenter.mapper.BonusEventLogMapper;
import com.soft1851.springboot.usercenter.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/10/8
 */
@Service
@RocketMQMessageListener(consumerGroup = "consumer",topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdbonusListener implements RocketMQListener<UserAddBonusMsgDto> {
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;


    @Override
    public void onMessage(UserAddBonusMsgDto userAddBonusMsgDto) {
        System.out.println("通过RocketMQ加积分");
        //1、为用户加积分
        Integer userId = userAddBonusMsgDto.getUserId();
        User user = this.userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus() + userAddBonusMsgDto.getBonus());
        this.userMapper.updateByPrimaryKeySelective(user);

        //2、写积分日志
        this.bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(userId)
                .value(userAddBonusMsgDto.getBonus())
                .event("CONTRIBUTE")
                .createTime(LocalDateTime.now())
                .description("投稿加积分")
                .build());
    }
}
