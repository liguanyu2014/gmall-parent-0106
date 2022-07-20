package com.atguigu.gmall.seckill;

import com.atguigu.gmall.common.annotation.EnableAutoHandleException;
import com.atguigu.gmall.common.annotation.EnableFeignInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignInterceptor
@EnableFeignClients(basePackages = "com.atguigu.gmall.feign.order")
@MapperScan(basePackages = "com.atguigu.gmall.seckill.mapper")
@EnableRabbit
@EnableAutoHandleException
@SpringCloudApplication
public class SeckillMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillMainApplication.class);
    }
}
