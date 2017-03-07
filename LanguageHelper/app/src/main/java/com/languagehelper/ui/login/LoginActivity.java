package com.languagehelper.ui.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.core.internet.ApiCallback;
import com.common.core.internet.ApiModel;
import com.common.utils.UIUtils;
import com.languagehelper.R;
import com.languagehelper.base.MActivity;
import com.languagehelper.http.module.BaseModel;
import com.languagehelper.http.task.UserApi;
import com.languagehelper.ui.home.MainActivity;

/**
 * Created by Administrator on 2017/3/7.
 */

public class LoginActivity extends MActivity implements View.OnClickListener {

    private EditText mAccountView;
    private EditText mPasswordView;
    private TextView mCommitView;

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_login);
        mAccountView = (EditText) findViewById(R.id.et_login_account);
        mPasswordView = (EditText) findViewById(R.id.et_login_password);
        mCommitView = (TextView) findViewById(R.id.tv_login_commit);
        mCommitView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_commit:
                if (check()) {
                    httpLogin();
                }
                break;
        }
    }

    private void httpLogin() {
        String accountStr = mAccountView.getText().toString();
        String passwordStr = mPasswordView.getText().toString();
        UserApi.login(accountStr, passwordStr, new ApiCallback<BaseModel>() {
            @Override
            public void onResult(BaseModel result) {
                String errorMsg = result.getErrorMsg();
                int statusCode = result.getStatusCode();
                UIUtils.showToastSafe(LoginActivity.this, errorMsg + statusCode + result.available());
                if (result.available()) {
                    //登录成功
                    MainActivity.start(LoginActivity.this);
                }

            }
        });
    }

    private boolean check() {
        String accountStr = mAccountView.getText().toString();
        String passwordStr = mPasswordView.getText().toString();
        if (TextUtils.isEmpty(accountStr)) {
            UIUtils.showToastSafe(this, "账号不能为空");
            return false;
        }
        if (TextUtils.isEmpty(passwordStr)) {
            UIUtils.showToastSafe(this, "密码不能为空");
            return false;
        }
        return true;
    }
}
