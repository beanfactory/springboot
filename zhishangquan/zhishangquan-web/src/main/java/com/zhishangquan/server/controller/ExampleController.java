package com.zhishangquan.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zhishangquan.server.interceptor.ExcludeUrl;
import com.zhishangquan.server.util.ResponseEntity;
import com.zhishangquan.server.util.ResponseEntityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 示例
 */
@Api(value = "示例接口", produces = "application/json")
@RestController
@RequestMapping("/example")
public class ExampleController extends BaseController {

  @ExcludeUrl
  @ApiOperation(value = "app分享，移动端分享，跳转界面", notes = "app分享，移动端分享，跳转界面")
  @RequestMapping(value = "/share/{userId}", method = RequestMethod.GET)
  public ModelAndView share(@PathVariable("userId") Long userId, HttpServletRequest request) {
    // 界面的路径为src/main/resouces/templates/share.ftl
    ModelAndView model = new ModelAndView("share");
    model.addObject("userId", userId);

    Map<String, String> map = new HashMap<String, String>();
    map.put("name", "张三");
    map.put("mobile", "11010010000");
    model.addObject("user", map);
    return model;
  }

  @ExcludeUrl
  @ApiOperation(value = "hello")
  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public ResponseEntity<Map<String, String>> hello(HttpServletRequest request) {
    Map<String, String> map = new HashMap<>();
    map.put("username", "张三");
    return ResponseEntityUtil.success(map);
  }

}
