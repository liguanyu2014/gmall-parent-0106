package com.atguigu.gmall.feign.user;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.user.UserAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("service-user")
@RequestMapping("/rpc/inner/user")
public interface UserFeignClient {

    /**
     * 获取用户地址列表
     * @return
     */
    @GetMapping("/address/list")
    Result<List<UserAddress>> getUserAddress();
}
