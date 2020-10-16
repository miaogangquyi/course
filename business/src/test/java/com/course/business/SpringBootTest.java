package com.course.business;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.getSqlSelect();


        //List<Chapter> chapterList = chapterTestMapper.selectBatchIds(Arrays.asList(new String[]{"18", "19"}));
        //List<Chapter> chapterList = chapterTestMapper.;
        //List<Chapter> chapterList = chapterTestMapper.selectList(queryWrapper
                //new QueryWrapper<Chapter>()
                //.lambda()
                //.eq(Chapter::getCourseId,"F3LoAsF1")
                //.eq(Chapter::getName,"大章03")
        //);
        //Map<String, Object> map = Maps.newHashMap();
        //map.put("name", "大章02");
        //List<Chapter> chapterList = chapterTestMapper.selectByMap(map);
        //chapterList.forEach(System.out::println);
    }
}

