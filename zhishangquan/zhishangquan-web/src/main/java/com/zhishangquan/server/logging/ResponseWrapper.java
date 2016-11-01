package com.zhishangquan.server.logging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Response响应封装
 */
public class ResponseWrapper extends HttpServletResponseWrapper {
  private long id;
  private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
  private PrintWriter writer = new PrintWriter(bos);

  public ResponseWrapper(Long requestId, HttpServletResponse response) {
    super(response);
    this.id = requestId;
  }

  @Override
  public ServletResponse getResponse() {
    return this;
  }

  @Override
  public PrintWriter getWriter() throws IOException {
    return new TeePrintWriter(super.getWriter(), writer);
  }

  public byte[] toByteArray() {
    return bos.toByteArray();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    return new ServletOutputStream() {
      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setWriteListener(WriteListener writeListener) {

      }

      private TeeOutputStream tee = new TeeOutputStream(ResponseWrapper.super.getOutputStream(), bos);

      @Override
      public void write(int b) throws IOException {
        tee.write(b);
      }
    };
  }


}
