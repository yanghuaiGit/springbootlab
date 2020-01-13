package com.yh.lettue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yh.lettue.repo") //扫描的mapper
public class LettueApplication {

    public static void main(String[] args) {
        SpringApplication.run(LettueApplication.class, args);
    }

}
