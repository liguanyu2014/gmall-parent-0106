package com.atguigu.gmall.product;

import com.atguigu.gmall.common.config.Swagger2Config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * springboot只会扫描自己主类所在的包和子包
 */
@Import(Swagger2Config.class)
@EnableTransactionManagement //开启基于注解的事务管理
@MapperScan(basePackages = "com.atguigu.gmall.product.mapper") //所有的mapper都要放在容器中
@SpringCloudApplication
public class ProductMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductMainApplication.class,args);
    }
}
