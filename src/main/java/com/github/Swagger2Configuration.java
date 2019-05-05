package com.github;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author : JieWang
 * @Date : Created in 2019年05月05日12:45
 * @Email : wangjie_hf@seczone.cn
 *
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.controller")) //生成所有API接口  
                .paths(PathSelectors.any())
                .build();
//                只生成被Api这个注解注解过的类接口            
//                 .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
//                 只生成被ApiOperation这个注解注解过的api接口
//                 .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot-demo接口文档")
                .description("springboot-demo相关接口的文档")
                .termsOfServiceUrl("https://github.com/wangjie66/springboot-demo/blob/master/README.md")
                .version("1.0")
                .build();
    }
}
