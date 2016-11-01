package com.zhishangquan.server.controller;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.zhishangquan.server.constants.Errors;
import com.zhishangquan.server.exception.BusinessException;
import com.zhishangquan.server.util.ResponseEntity;
import com.zhishangquan.server.util.ResponseEntityUtil;

/**
 * <p>
 * 异常响应处理。将异常包装为固定的格式并返回。
 * <p>
 * <p>
 * 指定格式为{@link ResponseEntity}。
 *
 * @author caoxudong
 * @see ResponseEntity
 * @since 0.1.0
 */
@ControllerAdvice
public class ControllerAssist {
  private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAssist.class);

  /**
   * 注册全局数据编辑器，若传递的数据为空字串 转成 null
   *
   * @param binder 数据绑定
   * @param request web请求
   */
  @InitBinder
  public void registerCustomEditors(WebDataBinder binder, WebRequest request) {
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
  }

  /**
   * 处理业务异常
   *
   * @param exception
   * @param request
   * @return
   */
  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResponseEntity<?> handleBusinessException(BusinessException exception, HttpServletRequest request) {
    LOGGER.info("业务异常 : {}",exception.getMessage(), exception);
    return ResponseEntityUtil.fail(HttpStatus.BAD_REQUEST.value(), exception.getCode(), exception.getLabel(), request);
  }

  // 4xx----------------------------------------------------

  /**
   * 处理请求参数验证异常
   *
   * @param exception
   * @param request
   * @return
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
      HttpServletRequest request) {
    LOGGER.info("处理请求参数验证异常", exception);
    Errors error = Errors.SYSTEM_REQUEST_PARAM_ERROR;
    String message = error.label;
    BindingResult bindingResult = exception.getBindingResult();
    if (bindingResult != null && bindingResult.hasErrors()) {
      List<ObjectError> objectErrorList = bindingResult.getAllErrors();
      if (!objectErrorList.isEmpty()) {
        message = objectErrorList.get(0).getDefaultMessage();
      }
    }
    return ResponseEntityUtil.fail(HttpStatus.BAD_REQUEST.value(), error.code, message, request);
  }

  /**
   * 处理客户端请求参数错误，返回响应码400
   *
   * @param request 请求对象
   * @param exception 异常对象
   * @param locale 地理信息
   * @return
   */
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler({InvalidParameterException.class, ServletRequestBindingException.class,
      MissingServletRequestParameterException.class, HttpMessageNotReadableException.class})
  @ResponseBody
  public ResponseEntity<?> handleArgumentException(HttpServletRequest request, Exception exception, Locale locale) {
    LOGGER.info("处理客户端请求参数错误", exception);
    Errors error = Errors.SYSTEM_REQUEST_PARAM_ERROR;
    return ResponseEntityUtil.fail(HttpStatus.BAD_REQUEST.value(), error.code, error.label, request);
  }

  // 5xx---------------------------------------------------------

  /**
   * 处理服务器端数据访问错误
   *
   * @param request 请求对象
   * @param exception 异常对象
   * @param locale 地理信息
   * @return
   */
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({SQLException.class, DataAccessException.class, DataAccessResourceFailureException.class,
      org.hibernate.exception.DataException.class, DataIntegrityViolationException.class})
  @ResponseBody
  public ResponseEntity<?> handleSQLException(HttpServletRequest request, Exception exception, Locale locale) {
    LOGGER.error("SQL exception", exception);
    Errors error = Errors.SYSTEM_ERROR;
    return ResponseEntityUtil.fail(HttpStatus.BAD_REQUEST.value(), error.code, error.label, request);
  }

  /**
   * 处理服务器端其他错误
   *
   * @param request 请求对象
   * @param exception 异常对象
   * @param locale 地理信息
   * @return
   */
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({Exception.class})
  @ResponseBody
  public ResponseEntity<?> handleAllException(HttpServletRequest request, RuntimeException exception, Locale locale) {
    LOGGER.error("Unhandled exception", exception);
    Errors error = Errors.SYSTEM_ERROR;
    return ResponseEntityUtil.fail(HttpStatus.BAD_REQUEST.value(), error.code, error.label, request);
  }

}
