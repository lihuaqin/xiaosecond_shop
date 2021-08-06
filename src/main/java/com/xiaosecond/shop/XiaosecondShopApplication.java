package com.xiaosecond.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@MapperScan("com.xiaosecond.shop.mapper")
@ServletComponentScan
public class XiaosecondShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaosecondShopApplication.class, args);
    }

}
