package com.languagehelper.config;

import android.support.v4.widget.SwipeRefreshLayout;

import com.common.core.internet.ApiConfiger;
import com.common.modules.property.PhoneProperties;
import com.common.utils.AndroidUtils;
import com.common.utils.JsonUtils;
import com.common.utils.StringUtils;
import com.common.utils.UIUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 服务配置
 *
 * @author kevin
 * @version v1.0
 * @since 2014-12/19/14
 */
public class MConfig extends ApiConfiger {

    private static final String API_ACCESS = "259eea423dee18c7b865b0777cd657cc";
    private static final String API_SECURITY_KEY = "6E820afd87518a475f83e8a279c0d367";;
    private static final String API_REGISTER_SERVER = "http://api.wecook.cn/";
    private static final String API_REGISTER_SERVER_TEST = "http://api.wecook.com.cn/";
//    private static final String API_REGISTER_SERVER_TEST = "http://192.168.4.50:81/";
    private static final String API_VERSION = "v3";

    //全局配置更新广播
    public static final String ACTION_UPDATE_GLOBAL_CONFIG_CHANGED = "cn.wecook.app.Update_Global_Config_Changed";

    private boolean isTestServer;

    /**
     * 版本号
     */
    private String version;

    /**
     * 是否升级
     */
    private boolean upgrade;

    /**
     * 统计服务
     */
    private String countServer;

    /**
     * 代理服务
     */
    private String proxyServer;

    /**
     * 数据服务
     */
    private String service="http://120.77.44.214:8080/demo";

    private static MConfig mConfig;

    private MConfig() {
//        isTestServer = (boolean) AndroidUtils.getMetaDataFromApplication(UIUtils.getContext(), "TEST_SERVER");
    }


    public String getVersion() {
        return version;
    }

    public String getService() {
        return service;
    }

    public String getCountServer() {
        return countServer;
    }

    public String getProxyServer() {
        return proxyServer;
    }

    public boolean isUpgrade() {
        return upgrade;
    }

    @Override
    public String getAccessKey() {
        return API_ACCESS;
    }

    @Override
    public String getSecurityKey() {
        return API_SECURITY_KEY;
    }

    public String getRegisterServer() {
        return (isTestServer ? API_REGISTER_SERVER_TEST : API_REGISTER_SERVER) + API_VERSION;
    }

    @Override
    public boolean available() {
        return super.available() && !StringUtils.isEmpty(service);
    }

    @Override
    public void parseJson(String json) throws JSONException {

    }

    public boolean isTest() {
        return isTestServer;
    }


    public synchronized static ApiConfiger getInstance() {
        if (mConfig==null) {
            mConfig = new MConfig();
        }
        return mConfig;
    }
}
