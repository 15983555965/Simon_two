package com.common.modules.property;

import android.content.Context;
import android.content.SharedPreferences;


import com.common.modules.BaseModule;

import java.util.HashSet;
import java.util.Set;

/**
 * 共享索引属性保存
 *
 * @author kevin
 * @version v1.0
 * @since 2014-10/3/14
 */
public class SharePreferenceProperties extends BaseModule {

    private static final String FILE_SHARE = "wecook_shared_file";

    private static SharedPreferences mSharePreferences;

    @Override
    public void setup(Context context) {
        super.setup(context);
        mSharePreferences = context.getSharedPreferences(FILE_SHARE, Context.MODE_PRIVATE);
    }

    @Override
    public void release() {
        super.release();
    }

    public static void set(String key, Object value) {
        SharedPreferences.Editor editor = mSharePreferences.edit();
        if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Set) {
            editor.putStringSet(key, (Set<String>) value);
        }
        editor.apply();
    }

    public static Object get(String key, Object def) {
        if (def instanceof Integer) {
            return mSharePreferences.getInt(key, (Integer) def);
        } else if (def instanceof String) {
            return mSharePreferences.getString(key, (String) def);
        } else if (def instanceof Long) {
            return mSharePreferences.getLong(key, (Long) def);
        } else if (def instanceof Boolean) {
            return mSharePreferences.getBoolean(key, (Boolean) def);
        } else if (def instanceof Float) {
            return mSharePreferences.getFloat(key, (Float) def);
        } else if (def instanceof Set) {
            return mSharePreferences.getStringSet(key, new HashSet<String>());
        }
        return "";
    }
}
