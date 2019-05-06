package com.github.common.cache;

import com.github.common.cache.repository.entity.Dictionary;
import com.github.common.cache.service.DictionaryService;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @Author : JieWang
 * @Date : Created in 2019年05月05日18:12
 * @Email : wangjie_hf@seczone.cn
 */
@Component
public class DictionaryUtils implements Observer {

    @Resource
    private DictionaryCache dictionaryCache ;
    @Resource
    private CacheManager cacheManager ;
    @Resource
    private DictionaryService dictionaryService;

    /**
     * 根据类型和code获取对象
     * @param type
     * @param code
     * @return
     */
    public Dictionary getDictionary(String type, String code) throws Exception{
        List<Dictionary> dictionaryList = new ArrayList<Dictionary>();
        if(!StringUtils.isEmpty(type) && !StringUtils.isEmpty(code)){
            for(Dictionary dictionary:  dictionaryCache.get()){
                if(type.equals(dictionary.getDicType()) && code.equals(dictionary.getDicCode())){
                    return dictionary ;
                }
            }
        }
        return new Dictionary() ;
    }

    public List<Dictionary> getDictionary(String type) throws Exception{
        List<Dictionary> dictionaryList = new ArrayList<Dictionary>();
        if(!StringUtils.isEmpty(type)){
            for(Dictionary dictionary:  dictionaryCache.get()){
                if(type.equals(dictionary.getDicType())){
                    dictionaryList.add(dictionary);
                }
            }
        }
        return dictionaryList ;
    }

    @Override
    public void update(Observable o, Object arg) {
       // dictionaryCache.reload() ;
        Cache cache = cacheManager.getCache("dicCache");
        cache.put("dc",dictionaryService.getDicList());
    }
}
