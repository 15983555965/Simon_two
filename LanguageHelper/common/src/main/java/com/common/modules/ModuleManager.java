package com.common.modules;

import android.content.Context;


import com.common.modules.filemaster.FileMaster;
import com.common.modules.image.ImageFetcher;
import com.common.modules.logstatistics.LogTracker;
import com.common.modules.network.NetworkState;
import com.common.modules.property.PhoneProperties;
import com.common.modules.property.SharePreferenceProperties;
import com.common.utils.JavaRefactorUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * 模块管理器
 *
 * @author kevin
 * @version v1.0
 * @since 2014-9/23/14
 */
public class ModuleManager {

    private static ModuleManager sInstance;

    private static Hashtable<Class, IModule> sModules = new Hashtable<Class, IModule>();

    private static List<Class<? extends IModule>> sDependMap = new ArrayList<>();

    /**
     * 模块初始化观察
     * 
     * @author kevin
     */
    public static interface ModuleRegisterObserver {

    	/**
    	 * 在模块初始化之前进行配置
    	 * @param context
    	 * @param module
    	 */
		public void onPrepare(Context context, IModule module);
    	
    }

    private ModuleManager() {
    }

    public static ModuleManager asInstance() {
        if (sInstance == null) {
            sInstance = new ModuleManager();
        }
        return sInstance;
    }

    /**
     * 解除注册所有的模块
     */
    public void unRegisterAllModules() {
        for (IModule module : sModules.values()) {
            module.release();
        }
    }

    /**
     * 注册所有的模块
     * @param context
     */
    public void registerAllModules(Context context, ModuleRegisterObserver observer) {
        addModule(SharePreferenceProperties.class);
        addModule(PhoneProperties.class);
        addModule(FileMaster.class);
//        addModule(LogTracker.class);
        addModule(NetworkState.class);
        addModule(ImageFetcher.class);
        addModule(NetworkState.class);

        for (Class<? extends IModule> orderCls : sDependMap) {
            try {
                IModule module = JavaRefactorUtils.newInstanceForClass(orderCls);
                sModules.put(module.getClass(), module);
                
                if (observer != null) {
                	observer.onPrepare(context, module);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }


        for (Class<? extends IModule> orderCls : sDependMap) {
            IModule module = sModules.get(orderCls);
            module.setup(context.getApplicationContext());
        }
    }

    /**
     * 添加模块
     * @param module
     */
    public static void addModule(Class<? extends IModule> module) {
        if (module != null) {
            sDependMap.add(module);
        }
    }

    /**
     * 获得对象实例
     * @param moduleCls
     * @return
     */
    public IModule getModule(Class<? extends IModule> moduleCls) {
        return sModules.get(moduleCls);
    }

}
