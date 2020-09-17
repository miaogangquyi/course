package com.course.system.controller;

import com.course.server.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: miaogang
 * @Date: 2020年09月15日
 * @Description: 这是system测试controller
 */
@RestController
public class SystemController {
    @Autowired
    TestService testService;
    @RequestMapping("/test")
    public List test(){
        return testService.list();

    }
}

