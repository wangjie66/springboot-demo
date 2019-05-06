package com.github;

import com.github.common.util.WildcardUtils;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import springfox.documentation.RequestHandler;
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
                //.apis(basePackage("com.github.*.controller")) //生成所有API接口  
                .apis(basePackage("com.*.controller")) //生成所有API接口  
                .paths(PathSelectors.any())
                .build();
//              只生成被Api这个注解注解过的类接口            
//              .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
//              只生成被ApiOperation这个注解注解过的api接口
//              .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return new Predicate<RequestHandler>() {
            public boolean apply(RequestHandler input) {
                return (Boolean)declaringClass(input).transform(handlerPackage(basePackage)).or(true);
            }
        };
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot-demo接口文档")
                .description("springboot-demo相关接口的文档")
                .termsOfServiceUrl("https://github.com/wangjie66/springboot-demo/blob/master/README.md")
                .version("1.0")
                .build();
    }


    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return new Function<Class<?>, Boolean>() {
            @Override
            public Boolean apply(Class<?> input) {
                return WildcardUtils.wildcardEquals(basePackage,ClassUtils.getPackageName(input));
                //return ClassUtils.getPackageName(input).startsWith(basePackage);
            }
        };
    }
}
