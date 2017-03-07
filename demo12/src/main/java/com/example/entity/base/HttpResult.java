package com.example.entity.base;

import javax.persistence.Transient;

/**
 * Created by Administrator on 2017/3/7.
 */
public class HttpResult extends BaseEntity {
    /**
     * 状态code
     */
    @Transient
    private int status;
    /**
     * 结果信息
     */
    @Transient
    private Object result;
    /**
     * 信息
     */
    @Transient
    private String info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
