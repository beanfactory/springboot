package com.zhishangquan.server.response;

/**
 * Created by Roger on 2015/12/23.
 */
public interface BeanCopier<S, D> {

  void copy(S source, D dest);
}
