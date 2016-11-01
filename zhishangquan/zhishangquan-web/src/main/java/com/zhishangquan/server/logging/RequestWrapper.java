package com.zhishangquan.server.logging;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.zhishangquan.server.util.FileUtil;



/**
 * Request请求包装
 */
public class RequestWrapper extends HttpServletRequestWrapper {
  private String body;
  private long id;

  public RequestWrapper(Long requestId, HttpServletRequest request) {
    super(request);
    this.id = requestId;
    try {
      body = FileUtil.toString(request.getReader());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    return new ResetServeletInputStream(body);
  }

  public String getBody() {
    return this.body;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  private class ResetServeletInputStream extends ServletInputStream {
    private final ByteArrayInputStream byteArrayInputStream;

    ResetServeletInputStream(String body) {
      byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
    }

    @Override
    public boolean isFinished() {
      return byteArrayInputStream.available() > 0;
    }

    @Override
    public boolean isReady() {
      return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {

    }

    @Override
    public int read() throws IOException {
      return byteArrayInputStream.read();
    }

  }

}


