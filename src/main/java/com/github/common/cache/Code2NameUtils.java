package com.github.common.cache;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月02日16:21
 * @Email : wangjie_hf@seczone.cn
 */
@Component
public class Code2NameUtils {

    @Resource
    private DictionaryUtils dictionaryUtils ;

    /**
     * 解析List里面的需要转换翻译的字段
     * @param list
     * @param <T>
     * @return
     */
    public <T> List<T> code2Name(List<T> list) {
        for (T t : list) {
            code2Name(t);
        }
        return list;
    }

    /**
     * 解析object里面的转换翻译的字段
     * @param t
     * @param <T>
     * @return
     */
    public <T> T code2Name(T t) {
        if(t == null){
            return t;
        }
        try {
            Class<?> objclass = t.getClass();
            Field[]  fields = objclass.getDeclaredFields();
            handleFields(t, objclass, fields);
            //遍历父类
            Class<?> supClass = objclass.getSuperclass();
            Field[] supField = supClass.getDeclaredFields();
            handleFields(t, supClass, supField);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 遍历所有对象属性字段，设置目标字段值
     * @param t
     * @param objclass
     * @param fields
     * @param <T>
     * @throws Exception
     */
    private <T> void handleFields(T t, Class<?> objclass, Field[] fields) throws Exception {
        for (Field fd : fields) {
            if (fd.isAnnotationPresent(Code2Name.class)) {
                Code2Name d = fd.getAnnotation(Code2Name.class);
                fd.setAccessible(true);
                Field codeField = null;
                try {
                    codeField = objclass.getDeclaredField(d.code());
                }catch (Exception e){
                    //从父类获取编码字段
                    Class<?> superClass = objclass.getSuperclass();
                    codeField = superClass.getDeclaredField(d.code());
                }
                String code = "";
                if (codeField != null) {
                    codeField.setAccessible(true);
                    Object temp = codeField.get(t);
                    if (temp != null) {
                        code = temp.toString();
                    }
                }
                //空值处理
                if(!StringUtils.isEmpty(d.emptyText()) && StringUtils.isEmpty(code)){
                    fd.set(t, d.emptyText());
                    continue;
                }
                String name = dictionaryUtils.getDictionary(d.type(),code).getDicName() ;
                if (!StringUtils.isEmpty(name)) {
                    fd.set(t, name);
                } else {
                    fd.set(t, "");
                }
            }
        }
    }

}
