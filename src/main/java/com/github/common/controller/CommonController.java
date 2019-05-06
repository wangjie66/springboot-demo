package com.github.common.controller;

import com.github.common.cache.DictionaryUtils;
import com.github.common.cache.repository.entity.Dictionary;
import com.github.common.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年05月06日9:00
 * @Email : wangjie_hf@seczone.cn
 */
@RestController
@RequestMapping(value = "/common")
@Api(value = "common", description = "options about common method", produces = "application/json",
        consumes = "application/json", tags = {"common"})
public class CommonController {

    @Autowired
    private DictionaryUtils dictionaryUtils ;

    @RequestMapping(value = "/getDic/{type}" ,method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ApiOperation(value = "get Dic", notes = "获取字典",httpMethod = "GET")
    public JsonResult getDic(@PathVariable String type) throws Exception {
        List<Dictionary> list = dictionaryUtils.getDictionary(type);
        return JsonResult.createBySuccess(list);
    }
    
    @RequestMapping(value = "/getDic/{type}/{code}" ,method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ApiOperation(value = "get Dic", notes = "获取字典",httpMethod = "GET")
    public JsonResult getDic(@PathVariable String type,@PathVariable String code) throws Exception {
        Dictionary list = dictionaryUtils.getDictionary(type, code);
        return JsonResult.createBySuccess(list);
    }
}
