package com.atguigu.gmall.user.service.impl;

import com.atguigu.gmall.common.constant.RedisConst;
import com.atguigu.gmall.common.execption.GmallException;
import com.atguigu.gmall.common.result.ResultCodeEnum;
import com.atguigu.gmall.common.util.JSONs;
import com.atguigu.gmall.common.util.MD5;
import com.atguigu.gmall.model.user.UserInfo;
import com.atguigu.gmall.model.vo.user.LoginSuccessRespVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.user.service.UserInfoService;
import com.atguigu.gmall.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public LoginSuccessRespVo login(UserInfo userInfo) {
        String loginName = userInfo.getLoginName();
        String passwd = userInfo.getPasswd();
        UserInfo loginUser = userInfoMapper.getUserByLoginNameAndPasswd(loginName, MD5.encrypt(passwd));
        //判断是否有对应用户
        if(loginUser == null){
            //登录失败
            throw new GmallException(ResultCodeEnum.LOGIN_FAIL);
        }
        //1.登录成功:生成令牌
        String token = UUID.randomUUID().toString().replace("-", "");
        //2.把用户信息按照令牌保存带redis  user:login:令牌 = 用户信息
        String loginUserToStr = JSONs.toStr(loginUser);
        //3.保存
        redisTemplate.opsForValue().set(RedisConst.USER_LOGIN_PREFIX+token,loginUserToStr,7, TimeUnit.DAYS);

        //4、准备登录成功后的返回数据
        LoginSuccessRespVo vo = new LoginSuccessRespVo();
        vo.setToken(token);
        vo.setNickName(loginUser.getNickName());

//        Map<String,String> userMap = new HashMap<>();
//        userMap.put("nickName",loginUser.getNickName());
//        userMap.put("id",loginUser.getId()+"");
//        vo.setUserInfo(userMap);

        return vo;
    }

    @Override
    public void logout(String token) {
        //用户退出，删除令牌信息
        redisTemplate.delete(RedisConst.USER_LOGIN_PREFIX+token);
    }
}




