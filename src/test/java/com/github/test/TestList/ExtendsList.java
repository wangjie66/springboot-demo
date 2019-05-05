package com.github.test.TestList;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月30日17:32
 * @Email : wangjie_hf@seczone.cn
 */
public class ExtendsList{

    public static void main(String[] args) {
        List list = new ArrayList(5) ;
        list.add("aa");
        list.add("bb");
        Target target = new Target() ;
        target.setData(list);
        target.addAll(list);
        System.out.println(target);
    }
}
