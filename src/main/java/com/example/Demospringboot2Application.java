package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//关闭自动配置数据源
@SpringBootApplication
@MapperScan(basePackages = {"com.example.dao"})
public class Demospringboot2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demospringboot2Application.class, args);
    }

}
