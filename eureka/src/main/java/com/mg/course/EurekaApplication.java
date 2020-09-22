package com.mg.course;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaServer
@Slf4j

public class EurekaApplication {

    //private static final Logger log = LoggerFactory.getLogger(EurekaApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication();
        Environment env = app.run(args).getEnvironment();
        log.info("启动成功");
        log.info("Eureka地址\tHttp://127.0.0.1:{}",env.getProperty("server.port"));
        //SpringApplication.run(EurekaApplication.class, args);
    }


}
