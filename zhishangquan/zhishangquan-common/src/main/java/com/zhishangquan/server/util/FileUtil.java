package com.zhishangquan.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件工具类
 * 
 * @author jzsong@uworks.cc
 */
public class FileUtil {
  private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

  private static String chartset = "UTF-8";
  
  private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

  /**
   * 获取文件分隔符
   * 
   * @return
   */
  public static String getFileSeparator() {
    return File.separator;
  }
  
  public static byte[] getBytes(String path) throws Exception {
	  InputStream is = new FileInputStream(new File(path));
	  byte[] iconBytes = new byte[is.available()];
	  is.read(iconBytes);
	  is.close();
	  return iconBytes;
  }

  /**
   * 读取内容
   * 
   * @param in
   * @return
   */
  public static String readString(InputStream in) {
    if (in == null) {
      return "";
    }

    StringBuffer out = new StringBuffer();
    byte[] b = new byte[4096];
    try {
      for (int n; (n = in.read(b)) != -1;) {
        out.append(new String(b, 0, n, chartset));
      }
    } catch (Exception e) {
      logger.info("", e);
    }
    return out.toString();
  }

  public static String toString(Reader input) throws IOException {
    StringWriter sw = new StringWriter();
    copy(input, sw);
    return sw.toString();
  }

  private static int copy(Reader input, Writer output) throws IOException {
    long count = copyLarge(input, output);
    if (count > Integer.MAX_VALUE) {
      return -1;
    }
    return (int) count;
  }

  private static long copyLarge(Reader input, Writer output) throws IOException {
    char[] buffer = new char[DEFAULT_BUFFER_SIZE];
    long count = 0;
    int n = 0;
    while (-1 != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
      count += n;
    }
    return count;
  }
}
