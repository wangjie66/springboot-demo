package com.github.domain.repository.provider;

import com.github.domain.repository.entity.Application;

import java.util.List;
import java.util.UUID;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月29日18:01
 * @Email : wangjie_hf@seczone.cn
 */
public class ApplicationProvider {

    public String batchInsert(List<Application>  applicationList) {
        StringBuffer sql = new StringBuffer("insert into application (app_uuid,name) values ");

        for(Application application : applicationList) {
            sql.append(String.format("('%s', '%s'),", UUID.randomUUID().toString().replaceAll("\\-", ""), application.getName()));
        }

        sql = sql.deleteCharAt(sql.length() -1);
        System.out.println(sql.toString());
        return sql.toString();
    }
}
