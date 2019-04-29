package com.github.domain.repository;

 import com.github.domain.repository.entity.Application;
 import com.github.domain.repository.provider.ApplicationProvider;
 import org.apache.ibatis.annotations.*;

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

    @InsertProvider(type=ApplicationProvider.class, method = "batchInsert")
    void addApplication(@Param(value = "list")List<Application> applications);
}
