package com.zhishangquan.server.swfilter.trie;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.zhishangquan.server.swfilter.WordFilter;

public class TrieWordFilter implements WordFilter {
  public static final Logger LOGGER = LoggerFactory.getLogger(TrieWordFilter.class);
  private TrieTree tree;

  /**
   * <p/>
   * 传递敏感词资源文件以初始化Trie树.
   *
   * @param wordsFile
   */
  public TrieWordFilter(File wordsFile) throws Exception {
    long t1 = System.currentTimeMillis();
    List<String> lines = Files.readLines(wordsFile, Charsets.UTF_8);
    tree = new TrieTree();
    tree.buildTree(lines);
    LOGGER.info("------------------------------");
    LOGGER.info("敏感词数量:" + lines.size());
    LOGGER.info("构造Trie树耗时:" + (System.currentTimeMillis() - t1) + "ms");
    LOGGER.info("Trie树占用内存: 共计" + calculateMemory(tree.getRootNode()) + "个TrieNode对象");
    LOGGER.info("------------------------------");
  }

  @Override
  public boolean hasSensitiveWords(String text) {
    TrieNode parent = this.tree.getRootNode();
    // 遍历整个字符串寻找敏感词
    // TODO 引入KMP可以不用逐个往后测试.
    int startPos = 0;
    int i = startPos;
    while (true) {
      // 只有这一种情况，表明找到了敏感词
      if (parent.isLeaf()) {
        return true;
      }
      if (i >= text.length()) {
        break;
      }
      int code = (int) text.charAt(i);
      TrieNode child = parent.findChildByCode(code);
      if (child == null) {
        // 这种情况下，表明当前查询失败，从root重新开始查.
        parent = this.tree.getRootNode();
        startPos++;
        i = startPos;
      } else {
        parent = child;
        i++;
      }
    }
    return false;
  }

  @Override
  public List<String> findSensitiveWords(String text) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String replaceSensitiveWords(String text, String placeholder) {
    TrieNode parent = this.tree.getRootNode();
    // 遍历整个字符串寻找敏感词
    // TODO 引入KMP可以不用逐个往后测试.
    int startPos = 0;
    int i = startPos;
    while (true) {
      // 只有这一种情况，表明找到了敏感词
      if (parent.isLeaf()) {
        // parent 表示敏感词的叶子节点
        StringBuilder sb = new StringBuilder(text);
        parent = parent.getParent();
        sb.replace(startPos, i, placeholder);
        text = sb.toString();
        i = startPos;
      }
      if (i >= text.length()) {
        break;
      }
      int code = (int) text.charAt(i);
      TrieNode child = parent.findChildByCode(code);
      if (child == null) {
        // 这种情况下，表明当前查询失败，从root重新开始查.
        parent = this.tree.getRootNode();
        startPos++;
        i = startPos;
      } else {
        parent = child;
        i++;
      }
    }
    return text;
  }

  private int calculateMemory(TrieNode node) {
    int sum = node == null ? 0 : 1;
    if (node != null && node.getChildren() != null) {
      for (TrieNode child : node.getChildren()) {
        sum += calculateMemory(child);
      }
    }
    return sum;
  }
}
