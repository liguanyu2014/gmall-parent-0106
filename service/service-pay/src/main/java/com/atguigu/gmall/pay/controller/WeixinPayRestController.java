package com.atguigu.gmall.pay.controller;

import com.atguigu.gmall.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/payment/weixinpay")
@RestController
public class WeixinPayRestController {

    @GetMapping("/submit/{orderId}")
    public Result getPayPage(@PathVariable("orderId") Long orderId){

        //要给前端展示一个微信二维码收银台
        return Result.ok();
    }
}
