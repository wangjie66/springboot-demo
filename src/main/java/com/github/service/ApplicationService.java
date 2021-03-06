package com.github.service;


import com.github.common.page.PageInfo;
import com.github.domain.repository.entity.Application;

import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月29日15:21
 * @Email : wangjie_hf@seczone.cn
 */
public interface ApplicationService {

    List<Application> getApplication();

    void addApplication(List<Application> applications);

    void updateApplication(Application application);

    PageInfo<Application> getApplicationPageList(PageInfo pageInfo);
}
