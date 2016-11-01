package com.zhishangquan.server.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 微信配置
 * @Date 2016年3月21日,下午9:52:14
 * @version v1.0
 * @author YYF
 */
@ConfigurationProperties(prefix = "weixin")
@Component
public class WeixinConfig {

    private static Logger logger = LoggerFactory.getLogger(WeixinConfig.class);

    private String appid;
    private String appsecret;
    private String partnerid;
    private String mchid;
    private String api_key;
    private String unifieorder_url;
    private String notify_url;
    private String query_url;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getUnifieorder_url() {
        return unifieorder_url;
    }

    public void setUnifieorder_url(String unifieorder_url) {
        this.unifieorder_url = unifieorder_url;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getQuery_url() {
        return query_url;
    }

    public void setQuery_url(String query_url) {
        this.query_url = query_url;
    }

    @PostConstruct
    public void init() {
        logger.debug(JSON.toJSONString(this));
    }
}
