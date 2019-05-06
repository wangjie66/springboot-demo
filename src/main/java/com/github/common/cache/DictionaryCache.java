package com.github.common.cache;

import com.github.common.cache.repository.entity.Dictionary;
import com.github.common.cache.service.DictionaryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月01日20:05
 * @Email : wangjie_hf@seczone.cn
 */
@Component
public class DictionaryCache {

    @Resource
    private DictionaryService dictionaryService;

    public DictionaryCache( ){

    }

    /**
     * 重新加载字典数据
     */
    @CachePut(value = "dicCache" , key= "'dc'")
    public List<Dictionary> reload(){
        return dictionaryService.getDicList();
     }

    /**
     * 清除所有字典数据
     */
    @CacheEvict(value = "dicCache" , allEntries = true)
    public void clearAll(){

    }

    @Cacheable(value = "dicCache" , key= "'dc'")
    public List<Dictionary> get(){
        return null ;
    }




}
