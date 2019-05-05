package com.github.controller;

import com.github.domain.repository.entity.Application;
import com.github.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月29日14:23
 * @Email : wangjie_hf@seczone.cn
 */
@RestController
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;


    @RequestMapping(value = "/select")
    public List<Application> select(){
        List<Application> applicationList = applicationService.getApplication() ;
        return applicationList;
    }

    @RequestMapping(value = "/insert")
    public void insert(){
        List<Application> applications = new ArrayList<Application>() ;
        Application application = new Application();
        application.setName("app1");
        applications.add(application) ;
        application = new Application();
        application.setName("app2");
        applications.add(application) ;
        applicationService.addApplication(applications) ;
    }


    @RequestMapping(value = "/update")
    public void update(){
        Application application = new Application();
        application.setId(1);
        applicationService.updateApplication(application) ;
    }
}
