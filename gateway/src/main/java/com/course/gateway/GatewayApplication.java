package com.course.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class GatewayApplication {

    //private static final Logger log = LoggerFactory.getLogger(SystemApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GatewayApplication.class);
        Environment env = app.run(args).getEnvironment();
        log.info("启动成功");
        log.info("Gateway 地址\tHttp://127.0.0.1:{}",env.getProperty("server.port"));
        //SpringApplication.run(EurekaApplication.class, args);
    }


}
