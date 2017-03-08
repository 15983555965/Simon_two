package com.example.utils;

import com.example.entity.Room;
import com.example.entity.base.BaseEntity;
import com.example.entity.base.HttpResult;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2017/3/7.
 */
public class HttpResultUtils {

    public static BaseEntity createResult(){
        return createResult(200,"success",null);
    }

    public static BaseEntity createResult(Object object){
        return createResult(200,"success",object);
    }
    public static BaseEntity createResultByCode(int code){
        return createResult(code,BaseEntity.getMsg(code));
    }

    public static BaseEntity createResult(int status,String info,Object object){
        HttpResult httpEntity = new HttpResult();
        httpEntity.setStatus(status);
        httpEntity.setInfo(info);
        httpEntity.setResult(object);
        return httpEntity;
    }

    public static BaseEntity createResult(int status, String info) {
         return createResult(status, info, null);
    }

}
