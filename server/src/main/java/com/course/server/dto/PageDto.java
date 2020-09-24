package com.course.server.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: miaogang
 * @Date: 2020年09月24日
 * @Description: 分页功能
 */
@Data
public class PageDto<T> {
    // 当前页面
    protected int page;
    // 每页条数
    protected int size;
    // 总条数
    protected long total;
    protected List<T> list;

}

