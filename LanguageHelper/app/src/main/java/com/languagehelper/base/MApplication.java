package com.languagehelper.base;

import android.app.Application;
import android.content.Context;

import com.common.base.BaseApplication;
import com.common.core.debug.DebugCenter;
import com.common.core.internet.Api;
import com.common.modules.IModule;
import com.languagehelper.config.MConfig;

/**
 * Created by Administrator on 2017/1/17.
 */

public class MApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //API初始化
        Api.init(MConfig.getInstance());
    }

    @Override
    public void onPrepareModule(Context context, IModule module) {
    }

}
