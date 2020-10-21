package com.course.system.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: miaogang
 * @Date: 2020年09月15日
 * @Description: 这是system测试controller
 */
@RestController
public class SystemController {
    @RequestMapping("/system")
    public String system() {
        return "111";
    }

}

