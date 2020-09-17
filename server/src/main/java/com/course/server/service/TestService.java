package com.course.server.service;

import com.course.server.domain.Test;
import com.course.server.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: miaogang
 * @Date: 2020年09月17日
 * @Description: 测试类
 */
@Service
public class TestService {
    @Autowired
    TestMapper testMapper;

    public List<Test> list(){
      return  testMapper.selectByExample(null);
    }

}

