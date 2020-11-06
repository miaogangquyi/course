package com.course.system.handler;

import com.course.server.exception.BusinessException;
import com.course.server.exception.ValidatorException;
import com.course.server.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResultVO validatorExceptionHandler(ValidatorException e) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-1);
        LOG.warn(e.getMessage());
        resultVO.setMsg("请求参数异常！");
        return resultVO;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultVO businessExceptionHandler(BusinessException e) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-3);
        LOG.error("业务异常：{}", e.getCode().getDesc());
        resultVO.setMsg(e.getCode().getDesc());
        return resultVO;
    }
}
