package com.course.business.admin.controller;

import com.course.server.dto.ChapterDto;
import com.course.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: miaogang
 * @Date: 2020年09月15日
 * @Description: 大章controller
 */
@RestController
@RequestMapping("/admin/chapter")
//@CrossOrigin
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @RequestMapping("/list")
    public List<ChapterDto> list() {
       return chapterService.list();
    }


}

