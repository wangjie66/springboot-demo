package com.github.common.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.util.Observable;

/**
 * baseCache只做动态监控的事情 热加载数据到缓存
 * 通过文件的修改时间变化
 * @Author : JieWang
 * @Date : Created in 2019年04月11日10:22
 * @Email : wangjie_hf@seczone.cn
 */
@Component
public class BaseCache extends Observable implements InitializingBean,Runnable {

    @Resource
    private DictionaryUtils dictionaryUtils ;

    private long fileTime ;

    @Override
    public void afterPropertiesSet() throws Exception {
        addObservers() ;
        //第一次要读取操作
        setChanged();
        notifyObservers();
        new Thread(this).start();
     }

    //public void afterSingletonsInstantiated() {
    //    addObservers() ;
    //    //第一次要读取操作
    //    setChanged();
    //    notifyObservers();
    //    new Thread(this).start();
    //}

     //注册观察者
     private void addObservers(){
         addObserver(dictionaryUtils) ;
     }


    @Override
    public void run() {
        WebApplicationContext webApplicationContext = null ;
        while(webApplicationContext==null){
            webApplicationContext = ContextLoader.getCurrentWebApplicationContext() ;
        }
        ServletContext servletContext = webApplicationContext.getServletContext() ;
        String path = servletContext.getRealPath("sca.properties")  ;
        //String path = "D:\\szproject\\sca\\seczone-sca-portal\\target\\classes\\sca.properties" ;
        File file = new File(path) ;
        fileTime = file.lastModified();
        while (true){
            //文件被修改了要通知所有监视类
            if(fileTime!=file.lastModified()){
                fileTime = file.lastModified() ;
                setChanged();
                notifyObservers();
            }
        }
    }
}
