package com.example.utils;

import com.example.entity.base.BaseEntity;

/**
 * Created by Administrator on 2017/3/7.
 */
public class HttpResultUtils {

    public static BaseEntity createResult(){
        return createResult(200,"success",null);
    }

    public static BaseEntity createResult(BaseEntity baseEntity){
        return createResult(200,"success",baseEntity);
    }
    public static BaseEntity createResult(int status,String info,BaseEntity baseEntity){
        if (baseEntity==null){
            baseEntity = new BaseEntity();
        }
        baseEntity.setStatus(status);
        baseEntity.setInfo(info);
        return baseEntity;
    }

    public static BaseEntity createResult(int status, String info) {
         return createResult(status, info, null);
    }
}
