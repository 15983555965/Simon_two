package com.example.entity.base;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/3/6.
 */
public class BaseEntity {
    public static final int CODE_200=200;
    public static final int CODE_3022 =3022;//进入房间失败
    public static final int CODE_3021 = 3021;//获取房间信息失败

    public static final int CODE_3023 = 3023;//没有删除房间权限

    public static HashMap<Integer,String> map=new HashMap<Integer,String>();

    public static void initMap(){
        map.put(CODE_200,"success");
        map.put(CODE_3022,"进入房间失败");
        map.put(CODE_3021,"获取房间信息失败");
        map.put(CODE_3023,"没有删除房间权限");
    }

    public static void checkInitMap(){
        if (map.size()<=0){
            initMap();
        }
    }

    public static String getMsg(int code){
        checkInitMap();
        return map.get(code);
    }
}
