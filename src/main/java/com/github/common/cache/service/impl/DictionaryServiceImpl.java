package com.github.common.cache.service.impl;

import com.github.common.cache.repository.DictionaryMapper;
import com.github.common.cache.repository.entity.Dictionary;
import com.github.common.cache.service.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月01日20:10
 * @Email : wangjie_hf@seczone.cn
 */
@Service("threadService")
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> getDicList() {

        return dictionaryMapper.getDicList();
    }
}
