package com.zhishangquan.server.util.acs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限控制注解
 * 
 * @author royguo
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ACS {
  /**
   * 是否允许匿名访问
   */
  boolean allowAnonymous() default false;
}
