package com.course.system.controller.admin;

import com.alibaba.fastjson.JSON;
import com.course.server.domain.User;
import com.course.server.dto.LoginUserDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.dto.UserDto;
import com.course.server.exception.BusinessException;
import com.course.server.exception.BusinessExceptionCode;
import com.course.server.service.UserService;
import com.course.server.util.JwtUtil;
import com.course.server.util.ValidatorUtil;
import com.course.server.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    public static final String BUSINESS_NAME = "用户";
    public static final String LOGIN_REDIS_KEY = "login.";


    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        userService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserDto userDto) {
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        // 保存校验
        ValidatorUtil.require(userDto.getLoginName(), "登陆名");
        ValidatorUtil.length(userDto.getLoginName(), "登陆名", 1, 50);
        ValidatorUtil.length(userDto.getName(), "昵称", 1, 50);
        ValidatorUtil.require(userDto.getPassword(), "密码");

        ResponseDto responseDto = new ResponseDto();
        userService.save(userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        userService.delete(id);
        return responseDto;
    }

    /**
     * 重置密码
     */
    @PostMapping("/save-password")
    public ResponseDto savePassword(@RequestBody UserDto userDto) {
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        ResponseDto responseDto = new ResponseDto();
        userService.savePassword(userDto);
        responseDto.setContent(userDto);
        return responseDto;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public ResultVO login(@RequestBody UserDto userDto, HttpServletRequest request) {
        LOG.info("用户登录开始");
        userDto.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        ResultVO resultVO = new ResultVO();

        // 根据验证码token去获取缓存中的验证码，和用户输入的验证码是否一致
        //String imageCode = (String) redisTemplate.opsForValue().get(userDto.getImageCodeToken());
        //LOG.info("从redis中获取到的验证码：{}", imageCode);
        //if (StringUtils.isEmpty(imageCode)) {
        //    responseDto.setSuccess(false);
        //    responseDto.setMessage("验证码已过期");
        //    LOG.info("用户登录失败，验证码已过期");
        //    return responseDto;
        //}
//        if (!imageCode.toLowerCase().equals(userDto.getImageCode().toLowerCase())) {
//            responseDto.setSuccess(false);
//            responseDto.setMessage("验证码不对");
//            LOG.info("用户登录失败，验证码不对");
//            return responseDto;
//        } else {
//            // 验证通过后，移除验证码
//            redisTemplate.delete(userDto.getImageCodeToken());
//        }

        LoginUserDto loginUserDto = userService.login(userDto);
        String token = JwtUtil.sign(loginUserDto.getLoginName(),loginUserDto.getId());
        loginUserDto.setToken(token);
        redisTemplate.opsForValue().set(LOGIN_REDIS_KEY+loginUserDto.getId(), JSON.toJSONString(loginUserDto), 3600, TimeUnit.SECONDS);
        resultVO.setData(loginUserDto);
        return resultVO;
    }
    @GetMapping("/getInfo")
    public ResultVO getInfo(HttpServletRequest httpServletRequest) {
        LOG.info("getInfo开始");
        String authHeader = httpServletRequest.getHeader(JwtUtil.TOKEN_HEADER);// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (authHeader == null || !authHeader.startsWith(JwtUtil.TOKEN_PREFIX)) {
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }
        //取得token
        String token = authHeader.substring(7);
        String username = JwtUtil.getUsername(token);
        User user = userService.selectByLoginName(username);
        user.setRoles(Arrays.asList("admin"));
        return new ResultVO().success(user);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public ResultVO logout(HttpServletRequest httpServletRequest) {
        LOG.info("getInfo开始");
        String authHeader = httpServletRequest.getHeader(JwtUtil.TOKEN_HEADER);// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (authHeader == null || !authHeader.startsWith(JwtUtil.TOKEN_PREFIX)) {
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }
        //取得token
        String token = authHeader.substring(7);
        String userId = JwtUtil.getUserId(token);
        redisTemplate.delete(LOGIN_REDIS_KEY+userId);
        LOG.info("从redis中删除token:{}", token);
        return new ResultVO().success();
    }
}
