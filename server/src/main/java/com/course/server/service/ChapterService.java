package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.domain.Test;
import com.course.server.dto.ChapterDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.mapper.TestMapper;
import org.apache.juli.logging.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: miaogang
 * @Date: 2020年09月17日
 * @Description: 测试类
 */
@Service
public class ChapterService {
    @Autowired
    ChapterMapper chapterMapper;

    public List<ChapterDto> list() {
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        List<ChapterDto> chapterDtoList = new ArrayList<ChapterDto>();
        return chapterList.stream().map(this::convert).collect(Collectors.toList());
    }

    private ChapterDto convert(Chapter chapter) {
        ChapterDto chapterDto = new ChapterDto();
        BeanUtils.copyProperties(chapter,chapterDto);
        return chapterDto;
    }

}

