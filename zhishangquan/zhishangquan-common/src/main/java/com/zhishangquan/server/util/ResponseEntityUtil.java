package com.zhishangquan.server.util;

import javax.servlet.http.HttpServletRequest;

import com.zhishangquan.server.constants.Errors;


/**
 * 响应返回数据工具类
 *
 * @author jzsong@uworks.cc
 */
public class ResponseEntityUtil {

  public static <T> ResponseEntity<T> success(T data) {
    ResponseEntity<T> entity = new ResponseEntity<T>();
    entity.setData(data);
    entity.setCode(Errors.SUCCESS.code);
    entity.setHttpStatus(HttpStatusCode.OK.value());
    entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
    return entity;
  }

  public static ResponseEntity<Void> success() {
    ResponseEntity<Void> entity = build();
    entity.setCode(Errors.SUCCESS.code);
    entity.setHttpStatus(HttpStatusCode.OK.value());
    return entity;
  }

  public static ResponseEntity<Void> fail(Integer httpStatus, Integer code, String message,
      HttpServletRequest request) {
    ResponseEntity<Void> entity = build();
    entity.setCode(code);
    entity.setHttpStatus(httpStatus);
    entity.setException(message);
    if (null != request) {
      entity.setPath(request.getRequestURI());
    }
    return entity;
  }

  private static ResponseEntity<Void> build() {
    ResponseEntity<Void> entity = new ResponseEntity<Void>();
    entity.setTimestamp(Long.valueOf(System.currentTimeMillis()));
    return entity;
  }

}
