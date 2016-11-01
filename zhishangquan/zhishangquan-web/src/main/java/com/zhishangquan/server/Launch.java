package com.zhishangquan.server;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;
import com.zhishangquan.server.config.AppConfig;

import io.swagger.annotations.Api;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring Boot启动主类
 * 
 * @author jzsong@uworks.cc
 */
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class Launch {
  /**
   * 应用程序入口
   */
  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Launch.class);
    app.addListeners(new SystemInit());
    app.run(args);
  }

  @Resource
  private AppConfig appConfig;

  /**
   * 生成API文档的入口
   */
  @Bean
  public Docket generateApi() {
    Docket docket = null;
    // 可以根据配置决定不做任何API生成
    if (!appConfig.getAllowGenerateApi()) {
      docket = new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.none())
          .build();
    }

    docket = new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        // 标示只有被 @Api 标注的才能生成API.
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        .paths(PathSelectors.any()).build()
        .pathMapping("/")
        .directModelSubstitute(LocalDate.class, String.class)
        // 遇到 LocalDate时，输出成String
        .genericModelSubstitutes(ResponseEntity.class)
        .alternateTypeRules(
            newRule(
                typeResolver.resolve(DeferredResult.class,
                    typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                typeResolver.resolve(WildcardType.class)))
        .useDefaultResponseMessages(false);
    return docket;
  }

  private ApiInfo apiInfo() {
    return new ApiInfo("YEONER APPSERVER系统接口", "YEONER APPSERVER系统接口", "1.0.0", "", "", "", "");
  }

  @Autowired
  private TypeResolver typeResolver;
}
