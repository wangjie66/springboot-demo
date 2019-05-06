package com.github.common.cache.repository;

import com.github.common.cache.repository.entity.Dictionary;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月01日20:11
 * @Email : wangjie_hf@seczone.cn
 */
public interface DictionaryMapper {

    @Select(value = "SELECT * FROM t_dictionary where dic_status = 0")
    @Results({
            @Result(property = "dicCode",  column = "dic_code"),
            @Result(property = "dicName",  column = "dic_name"),
            @Result(property = "dicDescription",  column = "dic_description"),
            @Result(property = "dicShortName",  column = "dic_shortname"),
            @Result(property = "dicStatus",  column = "dic_status"),
            @Result(property = "dicType",  column = "dic_type"),
            @Result(property = "dicOrder",  column = "dic_order"),
            @Result(property = "createTime",  column = "create_time"),
            @Result(property = "modifyTime", column = "modify_time")
    })
    public List<Dictionary> getDicList()  ;
}
