package com.zhishangquan.server.response;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

/**
 * Created by Roger on 2015/12/23. 响应数据辅助类
 */
public final class ResponseDataHelper {
  private static Logger LOGGER = LoggerFactory.getLogger(ResponseDataHelper.class);

  private ResponseDataHelper() {
  };

  public static <S, D> D convertObject(S sourceObject, Class<D> destClass, BeanCopier<S, D> beanCopier) {
    D dest = createObject(destClass);
    if (dest != null) {
      if (beanCopier != null) {
        beanCopier.copy(sourceObject, dest);
      } else {
        BeanUtils.copyProperties(sourceObject, dest);
      }
    }
    return dest;
  }

  public static <S, D> D convertObject(S sourceObject, Class<D> destClass) {
    return convertObject(sourceObject, destClass, null);
  }

  public static <S, D> List<D> convertList(List<S> sourceList, Class<D> destClass) {
    return convertList(sourceList, destClass, null);
  }

  public static <S, D> List<D> convertList(List<S> sourceList, Class<D> destClass, BeanCopier<S, D> beanCopier) {
    if (sourceList == null || sourceList.isEmpty()) {
      return null;
    }
    List<D> list = new ArrayList<>();
    for (S source : sourceList) {
      D dest = convertObject(source, destClass, beanCopier);
      if (dest != null) {
        list.add(dest);
      }
    }
    return list;
  }

  public static <S, D> PageResponseBean<D> convertPage(Page<S> sourcePage, Class<D> destClass,
      BeanCopier<S, D> beanCopier) {
    PageResponseBean<D> pageResponseBean = new PageResponseBean<>();
    if (sourcePage != null) {
      clonePagingInfo(pageResponseBean, sourcePage);// 拷贝分页信息
      cloneContent(pageResponseBean, sourcePage, destClass, beanCopier);// 拷贝分页数据
    }
    return pageResponseBean;
  }

  public static <S, D> PageResponseBean<D> convertPage(Page<S> sourcePage, Class<D> destClass) {
    return convertPage(sourcePage, destClass, null);
  }

  private static <D> D createObject(Class<D> clazz) {
    D dest = null;
    try {
      dest = clazz.newInstance();
    } catch (Exception e) {
      LOGGER.error("根据类【" + clazz.getName() + "】创建对象失败", e);
    }
    return dest;
  }

  private static <S, D> void cloneContent(PageResponseBean<D> pageResponseBean, Page<S> sourcePage, Class<D> destClass,
      BeanCopier<S, D> beanCopier) {
    List<D> content = new ArrayList<>();
    if (sourcePage.getContent() != null && sourcePage.getContent().size() > 0) {
      content = convertList(sourcePage.getContent(), destClass, beanCopier);
    }
    pageResponseBean.setContent(content);
  }

  private static <D> void clonePagingInfo(PageResponseBean<D> pageResponseBean, Page<?> page) {
    pageResponseBean.setTotalElements(page.getTotalElements());
    pageResponseBean.setTotalPages(page.getTotalPages());
    pageResponseBean.setFirst(page.isFirst());
    pageResponseBean.setLast(page.isLast());
    pageResponseBean.setSize(page.getSize());
    pageResponseBean.setNumber(page.getNumber());
    pageResponseBean.setNumberOfElements(page.getNumberOfElements());
  }
}
