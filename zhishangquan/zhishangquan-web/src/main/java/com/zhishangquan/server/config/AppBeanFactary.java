package com.zhishangquan.server.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import com.aliyun.oss.OSSClient;
import com.zhishangquan.server.interceptor.CrossDomainFilter;
import com.zhishangquan.server.logging.LoggingFilter;
import com.zhishangquan.server.swfilter.WordFilter;
import com.zhishangquan.server.swfilter.trie.TrieWordFilter;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

/**
 * 生成特殊bean的工厂bean, 有些第三方的Bean没有标注@component, 需要手工初始化.
 */
@Configuration
@EnableConfigurationProperties
public class AppBeanFactary {
  private Logger LOG = LoggerFactory.getLogger(getClass());
  @Resource
  private AppConfig appConfig;
  @Resource
  private MemcachedConfig memcachedConfig;
  @Resource
  private OSSConfig ossConfig;
  @Autowired
  private ResourceLoader resourceLoader;

  /**
   * 缓存
   * 
   * @return
   * @throws IOException
   */
  @Bean
  public MemcachedClient memcachedClient() throws IOException {
    MemcachedClient memcachedClient = null;
    if (memcachedConfig.isNeedAuth()) {
      AuthDescriptor ad = new AuthDescriptor(new String[] { "PLAIN" }, new PlainCallbackHandler(
          memcachedConfig.getUsername(), memcachedConfig.getPassword()));
      memcachedClient = new MemcachedClient(new ConnectionFactoryBuilder()
          .setProtocol(ConnectionFactoryBuilder.Protocol.BINARY).setAuthDescriptor(ad).build(),
          AddrUtil.getAddresses(memcachedConfig.getServers()));
    } else {
      memcachedClient = new MemcachedClient(new ConnectionFactoryBuilder().setProtocol(
          ConnectionFactoryBuilder.Protocol.BINARY).build(),
          AddrUtil.getAddresses(memcachedConfig.getServers()));
    }

    return memcachedClient;
  }

  /**
   * 存储-下载
   * 
   * @return
   */
  @Bean(name = "downloadOSSClient")
  public OSSClient downloadOSSClient() {
    return new OSSClient(ossConfig.getDownloadEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
  }

  /**
   * 存储-上传
   * 
   * @return
   */
  @Bean(name = "uploadOSSClient")
  public OSSClient uploadOSSClient() {
    return new OSSClient(ossConfig.getUploadEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
  }

  /**
   * 注册跨域支持过滤器
   */
  @Bean
  public FilterRegistrationBean registerCrossDomainFilter() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    CrossDomainFilter crossDomainFilter = new CrossDomainFilter();
    // 设置是否允许跨域访问
    crossDomainFilter.setAllowCrossDomain(appConfig.getAllowCrossDomainAccess());
    registrationBean.setFilter(crossDomainFilter);
    registrationBean.setOrder(1);
    return registrationBean;
  }

  /**
   * 注册请求和响应日志
   */
  @Bean
  public FilterRegistrationBean registerRequestLoggingFilter() {
    FilterRegistrationBean loggingBean = new FilterRegistrationBean();
    LoggingFilter loggingFilter = new LoggingFilter();
    loggingBean.setFilter(loggingFilter);
    return loggingBean;
  }

  @Bean(name = "wordFilter")
  public WordFilter wordFilter() {
    final String SENSITIVE_WORDS_RESOURCE = "classpath:sensitive-words/sensitive-words.txt";
    final org.springframework.core.io.Resource resource = resourceLoader.getResource(SENSITIVE_WORDS_RESOURCE);
    try {
      InputStream inputStream = resource.getInputStream();
      File tempFile = new File("sensitive-words.txt");
      OutputStream outputStream = new FileOutputStream(tempFile);
      try {
        IOUtils.copy(inputStream, outputStream);
      } catch (IOException e) {
        LOG.error(e.getMessage());
        LOG.error("加载敏感词列表错误，请检查文件是否存在！");
      } finally {
        outputStream.close();
      }
      LOG.info("加载WordFilter---------------");
      LOG.info("加载敏感词列表：" + resource.getFilename());
      return new TrieWordFilter(tempFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
