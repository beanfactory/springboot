package com.zhishangquan.server.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zhishangquan.server.constants.Errors;
import com.zhishangquan.server.constants.OCSKeys;
import com.zhishangquan.server.domain.User;
import com.zhishangquan.server.service.UserSessionService;
import com.zhishangquan.server.util.ExceptionUtil;

/**
 * 基础controller
 *
 * @author jzsong@uworks.cc
 */
public class BaseController {

  @Resource
  protected UserSessionService userSessionService;

  protected User getSessionUser(HttpServletRequest request) {
    return userSessionService.getSessionUser(request);
  }

  protected Long getSessionUserId(HttpServletRequest request) {
    return userSessionService.getSessionUser(request).getId();
  }

  protected void sessionUser(HttpServletRequest request, User user) {
    userSessionService.sessionUser(request, user);
  }

  protected void deleteSessionUser(HttpServletRequest request) {
    userSessionService.deleteSessionUser(request);
  }

  /**
   * 生成登录权限token
   *
   * @param request
   * @param userId
   * @return
   */
  protected String generateAccessToken(HttpServletRequest request) {
    return userSessionService.generateAccessToken(request);
  }

  /**
   * 设置登录权限的token
   *
   * @param request
   * @param accessToken
   */
  protected void setAccessTokenAttribute(HttpServletRequest request, String accessToken) {
    request.setAttribute(OCSKeys.ACCESS_TOKEN_HEADER_NAME, accessToken);
  }

  protected void throwException(int code, String codeLabel) {
    ExceptionUtil.throwException(code, codeLabel);
  }

  protected void throwException(Errors error) {
    ExceptionUtil.throwException(error);
  }

}
