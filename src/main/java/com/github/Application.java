package com.github;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月29日14:21
 * @Email : wangjie_hf@seczone.cn
 */
@SpringBootApplication(scanBasePackages = "com.github")
@MapperScan({"com.github.domain","com.github.common.cache.repository"})
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        System.out.println("正常启动了.");
    }
}
