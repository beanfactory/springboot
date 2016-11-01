package com.zhishangquan.server.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhishangquan.server.constants.Errors;
import com.zhishangquan.server.domain.User;
import com.zhishangquan.server.service.UserSessionService;
import com.zhishangquan.server.util.ExceptionUtil;

/**
 * 权限控制拦截器.
 *
 * @author jzsong@uworks.cc
 */
@Component
public class AccessControlInterceptor extends HandlerInterceptorAdapter {

  // private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
  protected UserSessionService userSessionService; 
	
  private static final List<String> noLoginResources = new ArrayList<String>() {
    private static final long serialVersionUID = 1L;

    {
      // swagger相关资源不需要登录
      add("/");
      add("/swagger-ui.html");
      add("/configuration");
      add("/swagger-resources");
      add("/api-docs");
      add("/v2/api-docs");
      add("/error");
    }
  };

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    // 不需要进行访问控制的资源过滤
    String uri = request.getRequestURI();
    for (String resource : noLoginResources) {
      if (uri.startsWith(resource)) {
        return true;
      }
    }
    if (handler instanceof HandlerMethod) {
      ExcludeUrl acs = ((HandlerMethod) handler).getMethodAnnotation(ExcludeUrl.class);
      // 判断是否允许匿名访问
      if (acs != null) {
        return true;
      }
    }
    //缓存获取验证
    User user = userSessionService.getSessionUser(request);
    if(user == null ){
    	ExceptionUtil.throwException(Errors.SYSTEM_NOT_LOGIN);
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
  }

}
