package com.zhishangquan.server.swfilter;

import java.util.List;

/**
 * <p/>
 * 技能词过滤器接口.
 *
 * @author royguo@uworks.cc
 */
public interface SkillWordFilter {
  /**
   * <p/>
   * 检查目标字符串是否有技能词.
   *
   * @param text
   * @return true or false
   */
  boolean hasSkillWords(String text);

  /**
   * <p/>
   *
   * @param text
   * @return List<String>
   */
  List<String> findSkillWords(String text);

  /**
   * <p/>
   *
   * @param text
   * @param placeholder 替换后的占位符
   * @return 被替换后的字符串
   */
  String replaceSkillWords(String text, String placeholder);
}
