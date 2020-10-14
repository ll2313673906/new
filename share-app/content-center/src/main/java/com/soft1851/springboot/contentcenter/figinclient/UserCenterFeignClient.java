package com.soft1851.springboot.contentcenter.figinclient;

import com.soft1851.springboot.contentcenter.configeration.UserCenterFeignConfiguration;
import com.soft1851.springboot.contentcenter.domain.dto.UserAddBonusMsgDto;
import com.soft1851.springboot.contentcenter.domain.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/9/29
 */

@FeignClient(name = "user-center",configuration = UserCenterFeignConfiguration.class)
public interface UserCenterFeignClient {
    /**
     * http://user-center/users/{id}
     *
     * @param id
     * @return UserDTO
     */
    @GetMapping("/user/{id}")
    UserDto findUserById(@PathVariable Integer id);


    /**
     * 加积分
     * @param userAddBonusMsgDto
     * @return
     */
    @RequestMapping(value = "/user/addBonus", method = RequestMethod.POST)
    UserAddBonusMsgDto addBonusById(@RequestBody UserAddBonusMsgDto userAddBonusMsgDto);
}
