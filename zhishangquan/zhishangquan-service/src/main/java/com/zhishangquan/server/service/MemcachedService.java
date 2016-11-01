package com.zhishangquan.server.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.spy.memcached.MemcachedClient;

/**
 * 缓存service
 * 
 * @author jzsong@uworks.cc
 */
@Service("memcachedService")
public class MemcachedService {

  @Resource
  private MemcachedClient memcachedClient;

  /**
   * 设置缓存
   * 
   * @param key：关键字
   * @param exprieTime：过期时间，单位秒（例如exprieTime=30，为30秒）
   * @param value：值
   */
  public void set(String key, int exprieTime, Object value) {
    memcachedClient.set(key, exprieTime, value);
  }

  /**
   * 删除缓存
   * 
   * @param key
   */
  public void delete(String key) {
    memcachedClient.delete(key);
  }

  /**
   * 获取缓存值
   * 
   * @param key
   * @return
   */
  public Object get(String key) {
    return memcachedClient.get(key);
  }

  /**
   * 获得缓存中的数据并重置其过期时间.
   * 
   * @param key
   * @param exprieTime
   */
  public Object getAndTouch(String key, int exprieTime) {
    Object value = memcachedClient.get(key);
    if (value != null) {
      memcachedClient.set(key, exprieTime, value);
    }
    return value;
  }

  /**
   * 缓存值+1，返回+1之后的值
   * 
   * @param key
   * @return
   */
  protected long incr(String key) {
    return memcachedClient.incr(key, 1);
  }

  /**
   * 缓存值-1，返回-1之后的值
   * 
   * @param key
   * @return
   */
  protected long decr(String key) {
    return memcachedClient.decr(key, 1, 1l);
  }

  /**
   * 批量获取缓存值
   * 
   * @param keys
   * @return
   */
  protected Map<String, Object> getBulk(List<String> keys) {
    return memcachedClient.getBulk(keys);
  }

}
