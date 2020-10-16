package com.course.business;

import com.course.server.domain.Chapter;
import com.course.server.mapper.ChapterTestMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: miaogang
 * @Date: 2020年10月15日
 * @Description: 测试类
 */
@Slf4j
@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest
public class SpringBootTest {
    @Autowired
    ChapterTestMapper chapterTestMapper;
    @Test
    public void tes(){
        Chapter chapter = chapterTestMapper.selectById("18");

         //chapterTestMapper.delete(chapter);
        log.info(chapter.toString());
    }
}

