package com.zhishangquan.server.swfilter;

import java.util.List;

/**
 * <p/>
 * 敏感词过滤器接口.
 *
 * @author royguo@uworks.cc
 */
public interface WordFilter {
  /**
   * <p/>
   * 检查目标字符串是否有敏感词.
   *
   * @param text
   * @return true or false
   */
  boolean hasSensitiveWords(String text);

  /**
   * <p/>
   * TODO 获取目标字符串中所有的敏感词.
   *
   * @param text
   * @return List<String>
   */
  List<String> findSensitiveWords(String text);

  /**
   * <p/>
   *
   * @param text
   * @param placeholder 替换后的占位符
   * @return 被替换后的字符串
   */
  String replaceSensitiveWords(String text, String placeholder);
}
