package com.atguigu.gmall.pay;

import com.atguigu.gmall.common.annotation.EnableAutoHandleException;
import com.atguigu.gmall.common.annotation.EnableFeignInterceptor;
import com.atguigu.gmall.common.annotation.EnableThreadPool;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableAutoHandleException
@EnableFeignInterceptor
@EnableFeignClients(basePackages = "com.atguigu.gmall.feign.order")
@EnableThreadPool
@SpringCloudApplication
public class PayMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayMainApplication.class);
    }
}
