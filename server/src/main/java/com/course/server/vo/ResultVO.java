package com.course.server.vo;

import lombok.Data;

/**
 * @Author: miaogang
 * @Date: 2020年11月05日
 * @Description: 统一返回类
 */
@Data
public class ResultVO<T> {
    /**
     * 状态码，比如0代表响应成功
     */
    private int code;
    /**
     * 响应信息，用来说明响应情况
     */
    private String msg;
    /**
     * 响应的具体数据
     */
    private T data;

    public ResultVO() {

    }

    public ResultVO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ResultVO success(T data) {
        return new ResultVO(0, "操作成功", data);
    }
    public ResultVO success() {
        return new ResultVO(0, "操作成功", null);
    }
    public ResultVO error() {
        return new ResultVO(-1, "操作失败",null);
    }
    public ResultVO tokenExpire() {
        return new ResultVO(-2, "token过期",null);
    }

}

