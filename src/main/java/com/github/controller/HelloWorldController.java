package com.github.controller;

import com.github.domain.repository.entity.Application;
import com.github.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月29日14:23
 * @Email : wangjie_hf@seczone.cn
 */
@RestController
public class HelloWorldController {

    @Autowired
    HelloService helloService ;


    @RequestMapping(value = "/hello")
    public String index(){
        List<Application> applicationList = helloService.getApplication() ;
        for(Application application :applicationList){
            int id = application.getId();
            String name = application.getShowName();;
        }
        return applicationList.size() +"";
    }
}
