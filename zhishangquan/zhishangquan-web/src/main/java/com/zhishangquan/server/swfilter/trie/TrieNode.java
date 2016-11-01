package com.zhishangquan.server.swfilter.trie;

import java.util.Iterator;
import java.util.TreeSet;

class TrieNode implements Comparable<TrieNode> {
  private int code;
  private TrieNode parent;
  private TreeSet<TrieNode> children;

  public TrieNode() {}

  public TrieNode(int code) {
    this.code = code;
  }

  public TrieNode(int code, TrieNode parent) {
    this.code = code;
    this.parent = parent;
  }

  /**
   * <p>
   * 添加新的孩子节点，按照顺序存放.
   * 
   * @param node
   */
  public void addChild(TrieNode node) {
    if (children == null) {
      children = new TreeSet<>();
    }
    children.add(node);
  }

  /**
   * <p>
   * 根据编码获取对应的孩子节点.
   * 
   * @param code
   * @return
   */
  public TrieNode findChildByCode(int code) {
    if (this.children == null || this.children.size() == 0) {
      return null;
    }
    // TODO 可以改成二分查找.
    Iterator<TrieNode> it = this.children.iterator();
    while (it.hasNext()) {
      TrieNode node = it.next();
      if (node.code == code) {
        return node;
      } else if (node.code < code) {
        continue;
      }
      break;
    }
    return null;
  }

  public boolean isLeaf() {
    if (this.children == null || this.children.size() == 0) {
      return true;
    }
    return false;
  }

  @Override
  public int compareTo(TrieNode o) {
    if (o == null) {
      throw new RuntimeException("不能比较空节点!");
    }
    if (this.code > o.code) {
      return 1;
    } else if (this.code < o.code) {
      return -1;
    } else {
      return 0;
    }
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public TrieNode getParent() {
    return parent;
  }

  public void setParent(TrieNode parent) {
    this.parent = parent;
  }

  public TreeSet<TrieNode> getChildren() {
    return children;
  }

  public void setChildren(TreeSet<TrieNode> children) {
    this.children = children;
  }

}
