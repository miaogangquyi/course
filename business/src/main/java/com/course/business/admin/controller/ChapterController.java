package com.course.business.admin.controller;

import com.course.server.dto.ChapterDto;
import com.course.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: miaogang
 * @Date: 2020年09月15日
 * @Description: 大章controller
 */
@RestController
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @RequestMapping("/chapter")
    public List<ChapterDto> chapter() {
       return chapterService.list();
    }


}

