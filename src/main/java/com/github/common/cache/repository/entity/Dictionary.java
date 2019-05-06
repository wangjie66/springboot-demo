package com.github.common.cache.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author : JieWang
 * @Date : Created in 2019年04月01日20:24
 * @Email : wangjie_hf@seczone.cn
 */
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id ;

    private String dicCode ;

    private String dicName ;

    private String dicDescription ;

    private String dicShortName ;

    private String dicStatus ;

    private String dicType ;

    private String dicOrder ;

    private Timestamp createTime;

    private Timestamp modifyTime;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public String getDicDescription() {
        return dicDescription;
    }

    public void setDicDescription(String dicDescription) {
        this.dicDescription = dicDescription;
    }

    public String getDicShortName() {
        return dicShortName;
    }

    public void setDicShortName(String dicShortName) {
        this.dicShortName = dicShortName;
    }

    public String getDicStatus() {
        return dicStatus;
    }

    public void setDicStatus(String dicStatus) {
        this.dicStatus = dicStatus;
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    public String getDicOrder() {
        return dicOrder;
    }

    public void setDicOrder(String dicOrder) {
        this.dicOrder = dicOrder;
    }

    public Timestamp getCteateTime() {
        return createTime;
    }

    public void setCteateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }
}
