package com.soft1851.springboot.usercenter.controller;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.soft1851.springboot.usercenter.domain.dto.*;
import com.soft1851.springboot.usercenter.domain.entity.User;
import com.soft1851.springboot.usercenter.service.UserService;
import com.soft1851.springboot.usercenter.util.JwtOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/9/29
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    private final JwtOperator jwtOperator;


    @GetMapping(value = "/{id}")
    public User findUserById(@PathVariable Integer id){
        log.info("我被调用了....");
        return userService.findById(id);
    }

    @GetMapping("/q")
    public User query(User user){
        return user;
    }

    @PostMapping("/addBonus")
    public UserAddBonusMsgDto addBonusById(@RequestBody  UserAddBonusMsgDto userAddBonusMsgDto){
         userService.addBonusById(userAddBonusMsgDto);
        System.out.println("通过feign调用加积分");
         return userAddBonusMsgDto;
    }

    @PostMapping(value = "/login")
    public LoginRespDto getUser(@RequestBody LoginDto loginDto){
        User user = this.userService.login(loginDto);
        //颁发token
        Map<String,Object> userInfo = new HashMap<>(3);
        userInfo.put("id",user.getId());
        userInfo.put("wxNickName",user.getWxNickName());
        userInfo.put("role",user.getRoles());
        String token = jwtOperator.generateToken(userInfo);

        log.info(
                "{}登录成功，生成的token = {},有效期到:{}",
                user.getWxNickName(),
                token,
                jwtOperator.getExpirationTime()
        );
        return LoginRespDto.builder()
                .user(UserRespDto.builder()
                .id(user.getId())
                .avatarUrl(user.getAvatarUrl())
                .wxNickName(user.getWxNickName())
                .bonus(user.getBonus())
                .build())
                .token(JwtTokenRespDto
                .builder()
                .token(token)
                .expirationTime(jwtOperator.getExpirationTime().getTime())
                .build())
                .build();
    }
}