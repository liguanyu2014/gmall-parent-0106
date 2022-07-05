package com.atguigu.gmall.user.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.user.UserInfo;
import com.atguigu.gmall.model.vo.user.LoginSuccessRespVo;
import com.atguigu.gmall.user.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
public class LoginController {

    UserInfoService userInfoService;

    //使用有参构造器完成自动注入
    public LoginController(UserInfoService userInfoService){
        this.userInfoService = userInfoService;
    }

    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    @PostMapping("/passport/login")
    public Result login(@RequestBody UserInfo userInfo){
        LoginSuccessRespVo vo = userInfoService.login(userInfo);
        return Result.ok(vo);
    }

    /**
     * 退出；前端会把用户的token放到请求头， token
     * @return
     */
    @GetMapping("/passport/logout")
    public Result logout(@RequestHeader("token") String token){
        userInfoService.logout(token);
        return Result.ok();
    }
}
