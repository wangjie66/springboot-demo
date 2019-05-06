package com.github.common.cache.service;

import com.github.common.cache.repository.entity.Dictionary;

import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月01日20:09
 * @Email : wangjie_hf@seczone.cn
 */
public interface DictionaryService {


    List<Dictionary> getDicList();
}
