/**
 * 
 */
package com.zhishangquan.server.swfilter;

import java.util.List;

/**
 * @author helei@uworks.cc
 *
 */
public interface SkillAssociateWordFilter {
  /**
   * <p/>
   *
   * @param text
   * @return List<String>
   */
  List<String> findSkillWords(String text);
}
