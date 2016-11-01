package com.zhishangquan.server.response;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PageResponseBean<T> implements Serializable {

  private static final long serialVersionUID = 6887389993060457824L;
  /**
   * 返回数据内容
   */
  @ApiModelProperty(value = "返回数据内容")
  private List<T> content;
  /**
   * 总条目数
   */
  private long totalElements;
  /**
   * 总页数
   */
  private int totalPages;
  /**
   * 是否是最后一页
   */
  private boolean last;
  /**
   * 当前页
   */
  private int number;
  /**
   * 当前页的条目数
   */
  private int size;
  private int numberOfElements;
  /**
   * 是否是第一页
   */
  private boolean first;

  public PageResponseBean() {
  }

  public PageResponseBean(Page<?> page) {
    super();
    this.totalElements = page.getTotalElements();
    this.totalPages = page.getTotalPages();
    this.first = page.isFirst();
    this.last = page.isLast();
    this.size = page.getSize();
    this.number = page.getNumber();
    this.numberOfElements = page.getNumberOfElements();
  }

  public List<T> getContent() {
    return content;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }

  public boolean isLast() {
    return last;
  }

  public void setLast(boolean last) {
    this.last = last;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getNumberOfElements() {
    return numberOfElements;
  }

  public void setNumberOfElements(int numberOfElements) {
    this.numberOfElements = numberOfElements;
  }

  public boolean isFirst() {
    return first;
  }

  public void setFirst(boolean first) {
    this.first = first;
  }
}
