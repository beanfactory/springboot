package com.zhishangquan.server.swfilter.trie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

class TrieTree {
  public static final Logger LOGGER = Logger.getLogger(TrieTree.class.getName());

  private TrieNode root = new TrieNode();
  public Map<Integer, Character> codes = new HashMap<>();

  private void addToTree(String word) {
    TrieNode parent = root;
    boolean skipSearch = false;
    for (int i = 0; i < word.length(); i++) {
      int code = (int) word.charAt(i);
      // 将code于汉子的对应关系缓存起来.
      codes.put(code, word.charAt(i));
      // 将当前code直接追加到parent下面.
      if (skipSearch) {
        TrieNode tmp = new TrieNode(code, parent);
        parent.addChild(tmp);
        parent = tmp;
        continue;
      }
      TrieNode node = parent.findChildByCode(code);
      if (node != null) {
        parent = node;
        continue;
      } else {
        // 再也找不到子节点，则直接后续把code直接追加到parent后.
        skipSearch = true;
        TrieNode tmp = new TrieNode(code, parent);
        parent.addChild(tmp);
        parent = tmp;
      }
    }
  }

  /**
   * <p/>
   * 构造Trie树.
   *
   * @param words
   */
  public void buildTree(List<String> words) {
    for (String word : words) {
      addToTree(word.trim());
    }
  }

  public TrieNode getRootNode() {
    return root;
  }
}
