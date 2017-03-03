package com.languagehelper.features.webview;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;

import com.languagehelper.R;
import com.languagehelper.base.MActivity;
import com.uikit.widget.TitleBar;


/**
 * 网页
 *
 * @author kevin
 * @version v1.0
 * @since 2014-10/18/14
 */
public class WebActivity extends MActivity {
    private static final String TAG = "jsbridge";
    public static final String EXTRA_URL = "extra_url";

    private WebView mWebView;
    private ImageView mClosePage;
    TitleBar mTitleBar;
    private String currentUrl;

    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        currentUrl = intent.getStringExtra(EXTRA_URL);
    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_web);
        mTitleBar = (TitleBar) findViewById(R.id.view_titlebar);
        mWebView = (WebView) findViewById(R.id.view_web);
        mWebView.getSettings().setBuiltInZoomControls(false);// 隐藏缩放按钮
        mWebView.setWebChromeClient(mChromeClient);
        mWebView.getSettings().setJavaScriptEnabled(true);
        initJsBridge();
        mClosePage = mTitleBar.getleftButton();
        mClosePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        mWebView.loadUrl(currentUrl);
    }
    @Override
    public View initActionBar() {
        return super.initActionBar();
    }

    private WebChromeClient mChromeClient = new WebChromeClient() {

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            mTitleBar.setTitle(title);
        }

    };

    private boolean back() {
        if (mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK&&back()){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void initJsBridge() {
        mWebView.addJavascriptInterface(new JavaScriptObject(this), JavaScriptObject.JS_METHOD_GET_CLASS);
    }
}
