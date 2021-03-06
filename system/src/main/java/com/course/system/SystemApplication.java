package com.course.system;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
@ComponentScan("com.course")
@MapperScan("com.course.server.mapper")
public class SystemApplication {

    //private static final Logger log = LoggerFactory.getLogger(SystemApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SystemApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("启动成功");
        log.info("System地址\tHttp://127.0.0.1:{}",env.getProperty("server.port"));
        //SpringApplication.run(EurekaApplication.class, args);

    }


}
