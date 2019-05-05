package com.github.controller;

import com.github.common.page.PageInfo;
import com.github.common.response.JsonResult;
import com.github.domain.repository.entity.Application;
import com.github.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月29日14:23
 * @Email : wangjie_hf@seczone.cn
 */

//@RestController = @Controller && @ResponseBody
@Controller
@Api(value = "application", description = "options about application", produces = "application/json",
        consumes = "application/json", tags = {"application"})
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;


    @RequestMapping(value = "/index" ,method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ApiOperation(value = "go to index", notes = "跳转页面",httpMethod = "GET")
    public String index(){
        return "index";
    }


    /**
     * 使用注解查询
     * @return
     */
    //@ApiImplicitParam(name = "id", value = "用户id", required=true, dataType = "String")
    @RequestMapping(value = "/select" ,method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ApiOperation(value = "select interface", notes = "查询app信息",httpMethod = "GET")
    @ResponseBody
    public JsonResult select(){
        List<Application> applicationList = applicationService.getApplication() ;
        return JsonResult.createBySuccess(applicationList);
    }

    /**
     * 使用注解 sqlprovider方式
     */
    @RequestMapping(value = "/insert")
    @ApiOperation(value = "select interface", notes = "插入app信息",httpMethod = "POST")
    public @ResponseBody JsonResult insert(){
        List<Application> applications = new ArrayList<Application>() ;
        for(int i =0 ;i<11 ; i++){
            Application application = new Application();
            application.setName("testapp"+i);
            applications.add(application) ;
        }
        applicationService.addApplication(applications) ;
        return JsonResult.createBySuccess() ;
    }


    /**
     * 使用sql配置文件
     */
    @RequestMapping(value = "/update")
    @ApiOperation(value = "select interface", notes = "修改app信息",httpMethod = "POST")
    @ResponseBody
    public JsonResult update(){
        Application application = new Application();
        application.setId(1);
        applicationService.updateApplication(application) ;
        return JsonResult.createBySuccess() ;
    }


    //http://localhost:8080/selectPage?pageNum=1&pageSize=10
    @ApiOperation(value = "select interface", notes = "分页查询app信息",httpMethod = "GET")
    @RequestMapping(value = "/selectPage")
    @ResponseBody
    public JsonResult selectPage(@Validated PageInfo pageInfo){
        PageInfo<Application> applicationList = applicationService.getApplicationPageList(pageInfo) ;
        return JsonResult.createBySuccess(applicationList);
    }
}
