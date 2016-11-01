package com.zhishangquan.server.logging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 日志打印
 */
public class LoggingFilter extends OncePerRequestFilter {

  private static Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

  private static final String REQUEST_PREFIX = "Request: ";
  private static final String RESPONSE_PREFIX = "Response: ";
  private LongAdder id = new LongAdder();

  private static final List<String> uriSuffixList = new ArrayList<String>();

  static {
    uriSuffixList.add(".html");
    uriSuffixList.add(".htm");
    uriSuffixList.add(".jsp");
    uriSuffixList.add(".js");
    uriSuffixList.add(".css");
    uriSuffixList.add(".png");
    uriSuffixList.add(".gif");
    uriSuffixList.add(".jpg");
    uriSuffixList.add(".bmp");
    uriSuffixList.add(".ico");
    // swagger-ui中的css后缀名
    uriSuffixList.add(".woff2");
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (logger.isDebugEnabled()) {
      id.increment();
      long requestId = id.longValue();
      request = new RequestWrapper(requestId, request);
      response = new ResponseWrapper(requestId, response);
    }
    try {
      filterChain.doFilter(request, response);
    } finally {
      if (logger.isDebugEnabled()) {
        logRequest(request);
        logResponse((ResponseWrapper) response, request);
      }
    }
  }

  private void logRequest(final HttpServletRequest request) {
    StringBuilder msg = new StringBuilder();
    msg.append(REQUEST_PREFIX);
    if (request instanceof RequestWrapper) {
      msg.append("request id=").append(((RequestWrapper) request).getId()).append("; ");
    }
    msg.append("请求头信息: ");
    Enumeration<String> headNames = request.getHeaderNames();
    while (headNames.hasMoreElements()) {
      String name = headNames.nextElement();
      msg.append(name).append("=").append(request.getHeader(name)).append(",");
    }
    msg.append("; ");

    HttpSession session = request.getSession(false);
    if (session != null) {
      msg.append("session id=").append(session.getId()).append("; ");
    }
    if (request.getMethod() != null) {
      msg.append("method=").append(request.getMethod()).append("; ");
    }
    if (request.getContentType() != null) {
      msg.append("content-type=").append(request.getContentType()).append("; ");
    }
    msg.append("uri=").append(request.getRequestURI());
    if (request.getQueryString() != null) {
      msg.append("?").append(request.getQueryString());
    }
    if (request instanceof RequestWrapper && !isMultipart(request) && !isBinaryContent(request)) {
      RequestWrapper requestWrapper = (RequestWrapper) request;
      try {
        msg.append("; body=").append(requestWrapper.getBody());
      } catch (Exception e) {
        logger.warn("Failed to parse request payload", e);
      }
    }
    logger.debug(msg.toString());
  }

  private boolean isBinaryContent(final HttpServletRequest request) {
    if (request.getContentType() == null) {
      return false;
    }
    return request.getContentType().startsWith("image") || request.getContentType().startsWith("video")
        || request.getContentType().startsWith("audio");
  }

  private boolean isMultipart(final HttpServletRequest request) {
    return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
  }

  private void logResponse(final ResponseWrapper response, HttpServletRequest request) {
    StringBuilder msg = new StringBuilder();
    msg.append(RESPONSE_PREFIX);
    msg.append("response id=").append((response.getId()));
    msg.append("; 响应头信息：");
    Collection<String> headNames = response.getHeaderNames();
    for (String h : headNames) {
      msg.append("; ").append(h).append("=").append(response.getHeader(h));
    }
    try {
      if (isPrintResponseBody(response, request)) {
        msg.append("; body=").append(new String(response.toByteArray(), response.getCharacterEncoding()));
      }
    } catch (Exception e) {
      logger.warn("Failed to parse response payload", e);
    }
    logger.debug(msg.toString());
  }

  private boolean isPrintResponseBody(ResponseWrapper response, HttpServletRequest request) {
    String contentType = response.getContentType();
    if (contentType != null) {
      if (contentType.contains("text/html")) {
        return false;
      }
      if (contentType.contains("application/javascript")) {
        return false;
      }
    }
    if (!validateUri(request)) {
      return false;
    }
    String acceptRanges = response.getHeader("Accept-Ranges");
    if (acceptRanges != null) {
      if ("bytes".equalsIgnoreCase(acceptRanges)) {
        return false;
      }
    }
    return true;
  }

  private boolean validateUri(HttpServletRequest request) {
    String uri = request.getRequestURI();
    if (uri == null) {
      return true;
    }
    int index = uri.lastIndexOf(".");
    if (index < 0) {
      return true;
    }
    uri = uri.substring(index);
    if (uriSuffixList.contains(uri)) {
      return false;
    }
    return true;
  }
}
