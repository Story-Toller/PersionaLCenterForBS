package com.yuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.yuan.dao")
public class YuanApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuanApplication.class, args);
    }

}
