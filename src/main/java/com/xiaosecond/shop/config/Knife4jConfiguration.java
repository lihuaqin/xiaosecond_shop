package com.xiaosecond.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 *
 * swagger2.x中启动swagger是使用 @EnableSwagger2注解
 * 在swagger3.x中拆分为 @EnableSwagger2WebMvc(传统servlet模式) @EnableSwagger2WebFlux(webflux模式) 分别适配不同模式
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("连接管理平台 Api接口文档")
                        .description("# 连接管理平台 RESTful APIs")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("test")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.xiaosecond.shop.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
