package com.yyg.sbt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *  Swagger地址是：http://localhost:8080/swagger-ui.html#/
 *   http://localhost:8080/doc.html#/
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                //当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.yyg.sbt.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("项目名称(收入与开票项目接口文档)")
                .contact(new Contact("yaoyige","http://www.baidu.com",""))
                .version("1.0")
                .description("API 描述")
                .build();
    }
}
