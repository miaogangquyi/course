package com.course.business.admin.controller;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PageDto list(@RequestBody PageDto pageDto) {
        chapterService.list(pageDto);
        return pageDto;
    }

    @RequestMapping("/save")
    public ChapterDto save(@RequestBody ChapterDto chapterDto) {
        chapterService.save(chapterDto);
        return chapterDto;
    }


}

