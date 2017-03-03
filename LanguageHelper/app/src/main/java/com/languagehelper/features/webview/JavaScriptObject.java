package com.languagehelper.features.webview;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.common.utils.LogUtils;
import com.common.utils.UIUtils;
import com.languagehelper.features.webview.config.HttpWebConfig;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/1/20.
 */
public class JavaScriptObject {
    public static final String JS_METHOD_GET_CLASS = "stub";
    private static final java.lang.String TAG = "M_JS";
    private final Context mContext;

    public JavaScriptObject(Context mContext) {
        this.mContext = mContext;
    }

    //    window.stub.jsCreateNewWebActivity('{\"type\":2,\"url\":\"'+url+'\",\"showAd\":'+showAd+'}');
//    {"type":2,"url":"http//:www.baidu.com","showAd":"showAd"}'
    @JavascriptInterface
    public void jsCreateNewWebActivity(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            int type = jsonObject.optInt("type");   //flid
            String showAd = jsonObject.optString("showAd");
            String url = jsonObject.optString("url");
            LogUtils.i("M_JS", type + "\n" + url + "\n" + showAd);
            String newUrl = createNewUrl(type, url, showAd);
            startWebActivity(newUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void jsProbationRead(String jsonStr) {
        LogUtils.i(TAG,jsonStr);
    }

    @JavascriptInterface
    public void jsDownLoad(String jsonStr) {
        LogUtils.i(TAG,jsonStr);
    }

    @JavascriptInterface
    public void jsCommonShowDialog(String jsonStr) {
        LogUtils.i(TAG,jsonStr);
    }

    @JavascriptInterface
    public void jsFetchVolume(String jsonStr) {
        LogUtils.i(TAG,jsonStr);
    }

    private void startWebActivity(String newUrl) {
        Intent intent = new Intent(mContext, WebActivity.class);
        intent.putExtra(WebActivity.EXTRA_URL, newUrl);
        mContext.startActivity(intent);
    }

    private String createNewUrl(int type, String url, String showAd) {
        return HttpWebConfig.WEB_SERVER + url + "&" + HttpWebConfig.WEB_CONFIG;
    }

}
