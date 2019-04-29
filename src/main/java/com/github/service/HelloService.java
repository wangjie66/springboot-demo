package com.github.service;


import com.github.domain.repository.entity.Application;

import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月29日15:21
 * @Email : wangjie_hf@seczone.cn
 */
public interface HelloService {

    List<Application> getApplication();

    void addApplication(List<Application> applications);
}
