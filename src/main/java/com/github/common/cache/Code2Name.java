package com.github.common.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @Author : JieWang
 * @Date : Created in 2019年04月02日10:55
 * @Email : wangjie_hf@seczone.cn
 */
@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Inherited
public @interface Code2Name {

    //字典类型
    String type() default "" ;
    //字典编码
    String code() default "" ;
    //为空默认显示
    String emptyText() default "" ;


}
