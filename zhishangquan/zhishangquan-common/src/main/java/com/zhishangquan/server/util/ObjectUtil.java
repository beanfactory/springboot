package com.zhishangquan.server.util;

import org.springframework.beans.BeanUtils;

/**
 * 对象工具类
 * 
 * @author jzsong@uworks.cc
 */
public class ObjectUtil {

  public static void copyProperties(Object source, Object target) {
    BeanUtils.copyProperties(source, target);
  }

}
