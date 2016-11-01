package com.zhishangquan.server.swfilter.trie;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.zhishangquan.server.swfilter.SkillAssociateWordFilter;

public class TrieSkillAssociateWordFilter implements SkillAssociateWordFilter {
  public static final Logger LOGGER = LoggerFactory.getLogger(TrieSkillAssociateWordFilter.class);
  private List<String> words;
  /**
   * <p/>
   * 传递技能词资源文件以初始化Trie树.
   *
   * @param wordsFile
   */
  public TrieSkillAssociateWordFilter(File wordsFile) throws Exception {
    words = Files.readLines(wordsFile, Charsets.UTF_8);
    LOGGER.info("------------------------------");
    LOGGER.info("技能词数量:" + words.size());
    LOGGER.info("------------------------------");
  }

  @Override
  public List<String> findSkillWords(String text) {

    List<String> skillWords = new ArrayList<String>();
    if(StringUtils.isBlank(text)){
      return skillWords;
    }
    
    for (String word : words) {
      if(word.toLowerCase().indexOf(text.toLowerCase()) > -1){
        skillWords.add(word);
      }
    }
    return skillWords;
  }

}
