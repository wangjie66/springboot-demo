package com.github.service.impl;

import com.github.domain.repository.ApplicationMapper;
import com.github.domain.repository.entity.Application;
import com.github.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月29日15:21
 * @Email : wangjie_hf@seczone.cn
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationMapper applicationMapper ;

    @Override
    public List<Application> getApplication() {
        List<Application> applicationList =  applicationMapper.getApplication();
        return applicationList ;
    }

    @Override
    public void addApplication(List<Application> applications) {
        applicationMapper.addApplication(applications);
    }

    @Override
    public void updateApplication(Application application) {
        applicationMapper.updateApplication(application) ;
    }
}
