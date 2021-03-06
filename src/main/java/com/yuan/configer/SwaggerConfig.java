package com.yuan.configer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2  //开启swagger2
public class SwaggerConfig {

    @Bean
//    配置了swagger的Docket的bean实例
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yuan.controller"))
//                过滤扫描路径
                .build();

    }

    //    配置swagger信息  apiInfo
    private ApiInfo apiInfo() {
//        作者信息

        Contact contact = new Contact("连源", "https://github.com/Giraffe0615", "3352892115@qq.com");
        return new ApiInfo(
                "个人中心",
                "连源的个人中心API文档",
                "v2.0",
                "https://yun.cn",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}