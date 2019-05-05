package com.github.test.TestList;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月30日17:32
 * @Email : wangjie_hf@seczone.cn
 */
public class Target<E> extends ArrayList{

    private String name ="target_name" ;

    private E data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder() ;
        if(this.data!=null && this.data instanceof List){
            List<String> list = (List) this.data;
            for(String s : list){
                sb.append(this.name + ":" + s);
            }
        }
        return  sb.toString() ;
    }

}
