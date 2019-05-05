package com.github.controller;

import com.github.Swagger2Configuration;
import com.github.common.page.PageInfo;
import com.github.domain.repository.entity.Application;
import com.github.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月29日14:23
 * @Email : wangjie_hf@seczone.cn
 */
@RestController
@Api(value = "container", description = "options about application", produces = "application/json",
        consumes = "application/json", tags = {"application"})
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;


    /**
     * 使用注解查询
     * @return
     */
    @ApiOperation(value = "select interface", notes = "查询app信息",httpMethod = "GET")
    //@ApiImplicitParam(name = "id", value = "用户id", required=true, dataType = "String")
    @RequestMapping(value = "/select" ,method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public List<Application> select(){
        List<Application> applicationList = applicationService.getApplication() ;
        return applicationList;
    }

    /**
     * 使用注解 sqlprovider方式
     */
    @RequestMapping(value = "/insert")
    @ApiOperation(value = "select interface", notes = "插入app信息",httpMethod = "POST")
    public void insert(){
        List<Application> applications = new ArrayList<Application>() ;
        for(int i =0 ;i<11 ; i++){
            Application application = new Application();
            application.setName("testapp"+i);
            applications.add(application) ;
        }
        applicationService.addApplication(applications) ;
    }


    /**
     * 使用sql配置文件
     */
    @RequestMapping(value = "/update")
    @ApiOperation(value = "select interface", notes = "修改app信息",httpMethod = "POST")
    public void update(){
        Application application = new Application();
        application.setId(1);
        applicationService.updateApplication(application) ;
    }


    //http://localhost:8080/selectPage?pageNum=1&pageSize=10
    @ApiOperation(value = "select interface", notes = "分页查询app信息",httpMethod = "GET")
    @RequestMapping(value = "/selectPage")
    public PageInfo<Application> selectPage(@ModelAttribute PageInfo pageInfo){
        PageInfo<Application> applicationList = applicationService.getApplicationPageList(pageInfo) ;
        return applicationList;
    }
}
