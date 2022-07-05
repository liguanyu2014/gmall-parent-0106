package com.atguigu.gmall.user.service;

import com.atguigu.gmall.model.user.UserInfo;
import com.atguigu.gmall.model.vo.user.LoginSuccessRespVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    LoginSuccessRespVo login(UserInfo userInfo);

    /**
     * 退出；前端会把用户的token放到请求头， token
     * @return
     */
    void logout(String token);
}
