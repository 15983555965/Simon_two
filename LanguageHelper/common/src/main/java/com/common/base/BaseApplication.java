package com.common.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;

import com.common.modules.IModule;
import com.common.modules.ModuleManager;
import com.common.modules.image.ImageFetcher;
import com.common.utils.AndroidUtils;
import com.common.utils.UIUtils;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by lipan on 2014/7/11.
 */
public abstract class BaseApplication extends Application {
	/** 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了 */
	private static BaseApplication mInstance;
	/** 主线程ID */
	private static int mMainThreadId = -1;
	/** 主线程ID */
	private static Thread mMainThread;
	/** 主线程Handler */
	private static Handler mMainThreadHandler;
	/** 主线程Looper */
	private static Looper mMainLooper;
	private static boolean mResume;
	private static boolean mPendingKill;

	@Override
	public void onCreate() {
		mMainThreadId = android.os.Process.myTid();
		mMainThread = Thread.currentThread();
		mMainThreadHandler = new Handler();
		mMainLooper = getMainLooper();
		mInstance = this;
		initConfig();
		super.onCreate();
	}

	/**
	 * 初始化配置
	 */
	private void initConfig() {
		ModuleManager.asInstance().registerAllModules(this, new ModuleManager.ModuleRegisterObserver() {

			@Override
			public void onPrepare(Context context, IModule module) {
				onPrepareModule(context, module);
			}
		});
	}

	public static BaseApplication getApplication() {
		return mInstance;
	}

	/** 获取主线程ID */
	public static int getMainThreadId() {
		return mMainThreadId;
	}

	/** 获取主线程 */
	public static Thread getMainThread() {
		return mMainThread;
	}

	/** 获取主线程的handler */
	public static Handler getMainThreadHandler() {
		return mMainThreadHandler;
	}

	/** 获取主线程的looper */
	public static Looper getMainThreadLooper() {
		return mMainLooper;
	}


	public abstract void onPrepareModule(Context context, IModule module);

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		ImageFetcher.asInstance().onLowMemory(-1);
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
		ImageFetcher.asInstance().onLowMemory(level);
	}
	/**
	 * 隐藏软件
	 */
	public static void hideApp() {
		//模拟HOME键
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 注意
		intent.addCategory(Intent.CATEGORY_HOME);
		mInstance.startActivity(intent);
	}

	/**
	 * 退出软件
	 */
	public static void quitApp() {
		quitApp(true);
	}

	/**
	 * 强制退出软件
	 */
	public static void quitApp(boolean waitLog) {
		ModuleManager.asInstance().unRegisterAllModules();
		if (AndroidUtils.isMainProcess(mInstance)) {

			if (!waitLog) {
				killAll();
				return;
			}
			new Thread(new Runnable() {
				@Override
				public void run() {
					mResume = false;
					mPendingKill = true;

					if (!mResume) {
						UIUtils.post(new Runnable() {
							@Override
							public void run() {
								mPendingKill = false;
								killAll();
							}
						});
					}
				}
			}).start();
		} else {
			killAll();
		}

	}
	public static void resumeApp() {
		mResume = true;
	}

	public static boolean isPendingKillApp() {
		return mPendingKill;
	}
	private static void killAll() {
		mInstance.onDestroy();

		List<Integer> pIds = AndroidUtils.getAllProcessId(UIUtils.getContext(),
				new String[]{UIUtils.getContext().getPackageName() + ":push"});
		for (int pid : pIds) {
			Process.killProcess(pid);
		}
	}
	/**
	 * 销毁
	 */
	protected void onDestroy() {

	}
}
