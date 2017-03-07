package com.example.utils;

import com.example.entity.base.BaseEntity;
import com.example.entity.base.HttpResult;

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
    public static BaseEntity createResult(int status,String info,BaseEntity entity){
        HttpResult httpEntity = new HttpResult();
        httpEntity.setStatus(status);
        httpEntity.setInfo(info);
        httpEntity.setResult(entity);
        return httpEntity;
    }

    public static BaseEntity createResult(int status, String info) {
         return createResult(status, info, null);
    }
}
