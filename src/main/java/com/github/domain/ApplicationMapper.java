package com.github.domain;

 import com.github.domain.repository.entity.Application;
 import org.apache.ibatis.annotations.Mapper;
 import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
 import org.springframework.stereotype.Repository;

 import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月29日15:23
 * @Email : wangjie_hf@seczone.cn
 */
@Mapper
public interface ApplicationMapper {


    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "showName", column = "show_name")
    })
    @Select("SELECT id,show_name FROM application")
    List<Application> getApplication();
}
