package com.zhishangquan.server.request;

import java.io.Serializable;

import javax.validation.constraints.Min;

import org.springframework.data.domain.Sort;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页请求数据
 * 
 * @author jzsong@uworks.cc
 */
@ApiModel
public class PageRequestBean implements Serializable {

  private static final long serialVersionUID = -8822923831226295853L;

  private static final int DEFAULT_PAGE = 0;
  private static final int DEFAULT_SIZE = 10;

  /**
   * 当前页，默认0
   */
  @Min(value = 0, message = "page cannot be less then 0")
  @ApiModelProperty(value = "当前页，首页为0")
  private int page = DEFAULT_PAGE;

  /**
   * 每页多少条，默认10条
   */
  @Min(value = 1, message = "size cannot be less then 1")
  @ApiModelProperty(value = "每页显示条数，须大于0，默认10条")
  private int pageSize = DEFAULT_SIZE;

  /**
   * 排序
   */
  @ApiModelProperty(value = "一定不能传")
  private Sort sort;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public Sort getSort() {
    return sort;
  }

  public void setSort(Sort sort) {
    this.sort = sort;
  }
}
