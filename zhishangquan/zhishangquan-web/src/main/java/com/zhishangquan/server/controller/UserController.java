package com.zhishangquan.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhishangquan.server.domain.User;
import com.zhishangquan.server.interceptor.ExcludeUrl;
import com.zhishangquan.server.request.UserLoginRequestBean;
import com.zhishangquan.server.request.UserRegisterRequestBean;
import com.zhishangquan.server.response.ResponseDataHelper;
import com.zhishangquan.server.response.UserResponseBean;
import com.zhishangquan.server.service.UserService;
import com.zhishangquan.server.util.ResponseEntity;
import com.zhishangquan.server.util.ResponseEntityUtil;

@Api(value = "用户管理", produces = "application/json")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
  @Resource
  private UserService userService;

  @ApiOperation(value = "注册", notes = "注册")
  @ExcludeUrl
  @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserResponseBean> register(@Valid @RequestBody UserRegisterRequestBean bean,
      HttpServletRequest request) {
    User user = userService.register(bean);
    UserResponseBean userBean = ResponseDataHelper.convertObject(user, UserResponseBean.class);
    String accessToken = generateAccessToken(request);
    setAccessTokenAttribute(request, accessToken);
    userBean.setAccessToken(accessToken);
    user.setPassword(null);
    sessionUser(request, user);
    return ResponseEntityUtil.success(userBean);
  }
  
  @ApiOperation(value = "登录", notes = "登录")
  @ExcludeUrl
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseEntity<UserResponseBean> login(@Valid @RequestBody UserLoginRequestBean bean,
  		HttpServletRequest request) {
  	User user = userService.login(bean);
  	UserResponseBean userBean = ResponseDataHelper.convertObject(user, UserResponseBean.class);
  	String accessToken = generateAccessToken(request);
  	setAccessTokenAttribute(request, accessToken);
  	userBean.setAccessToken(accessToken);
  	user.setPassword(null);
  	sessionUser(request, user);
  	return ResponseEntityUtil.success(userBean);
  }

  @ApiOperation(value = "退出登录", notes = "退出登录", consumes = "application/json")
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public ResponseEntity<Void> logout(HttpServletRequest request) {
    deleteSessionUser(request);
    return ResponseEntityUtil.success();
  }

}
