package com.github;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月29日14:21
 * @Email : wangjie_hf@seczone.cn
 */
@SpringBootApplication(scanBasePackages = "com.github")
@MapperScan("com.github.domain")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
