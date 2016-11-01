package com.zhishangquan.server.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.zhishangquan.server.constants.Errors;
import com.zhishangquan.server.constants.OCSKeys;
import com.zhishangquan.server.domain.User;
import com.zhishangquan.server.util.ExceptionUtil;
import com.zhishangquan.server.util.StringUtil;

/**
 * @author jzsong@uworks.cc
 */
@Service("userSessionService")
public class UserSessionService {

  @Resource
  private MemcachedService memcachedService;

  private static final String ACCESS_TOKEN_HEADER_NAME = OCSKeys.ACCESS_TOKEN_HEADER_NAME;

  private static String USER_KEY = OCSKeys.ZHISHANGQUAN_SERVER_USER_KEY;
  private static int USER_EXP_KEY = OCSKeys.ZHISHANGQUAN_SERVER_USER_EXP_KEY;

  /**
   * <pre>
   * 设置用户信息到缓存中
   *
   * <pre>
   *
   * @param request
   * @param user
   */
  public void sessionUser(HttpServletRequest request, User user) {
    String key = getUserSessionKey(request);
    memcachedService.set(key, USER_EXP_KEY, user);
  }

  /**
   * <pre>
   * 获取缓存用户，不为空，重新设置缓存中用户的过期时间
   * </pre>
   *
   * @param request
   * @return
   */
  public User getSessionUser(HttpServletRequest request) {
    String key = getUserSessionKey(request);
    Object obj = memcachedService.get(key);
    if (obj == null) {
      ExceptionUtil.throwException(Errors.SYSTEM_NOT_LOGIN);
    }
    User user = (User) obj;
    if (user != null) {
      memcachedService.set(key, USER_EXP_KEY, user);
    }
    return user;
  }

  /**
   * 删除缓存中得用户信息
   *
   * @param request
   */
  public void deleteSessionUser(HttpServletRequest request) {
    String key = getUserSessionKey(request);
    memcachedService.delete(key);
  }

  /**
   * 获取用户缓存key
   *
   * @param request
   * @return
   */
  private String getUserSessionKey(HttpServletRequest request) {
    String key = USER_KEY + getSessionKey(request);
    return key;
  }

  /**
   * <pre>
   * 获取缓存key
   * 同时使用，使用token保存登录信息，优先使用token，如果获取失败则取session
   * </pre>
   *
   * @param request
   */
  private String getSessionKey(HttpServletRequest request) {
    String sessionId = "";
    Object sessionIdAttribute = request.getAttribute(ACCESS_TOKEN_HEADER_NAME);
    if (StringUtil.isNotBlank(sessionIdAttribute)) {
      sessionId = sessionIdAttribute.toString();
    }
    if (StringUtil.isBlank(sessionId)) {
      sessionId = request.getHeader(ACCESS_TOKEN_HEADER_NAME);
    }
    return sessionId;
  }

  /**
   * 生成登录权限token
   *
   * @param request
   * @return
   */
  public String generateAccessToken(HttpServletRequest request) {
    return request.getSession().getId() + StringUtil.uuidNotLine();
  }

}
