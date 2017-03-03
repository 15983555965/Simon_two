package com.uikit.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.utils.LogUtils;
import com.uikit.R;


/**
 * Created by qianjin on 2015/4/24.
 */
public class TitleBar extends FrameLayout {

    private final Context mContext;
    private ImageView leftButton;
    public ImageView rightButton;
    private TextView tvTitle;
    private TextView tvRightButton;
    private TextView tvRightButton2;
    ImageView redDot;
    RelativeLayout rlTitle;

    public TitleBar(Context context) {
        this(context,null);
    }

    public TitleBar(final Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        init();
        initView();
    }

    private void init() {

    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.title_bar, this);
        leftButton = (ImageView) findViewById(R.id.btn_left);
        rightButton = (ImageView) findViewById(R.id.btn_right);
        redDot = (ImageView) findViewById(R.id.iv_red_dot);
        tvRightButton = (TextView) findViewById(R.id.btn_text_right);
        tvRightButton2 = (TextView) findViewById(R.id.btn_text_right2);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof Activity) {
                    ((Activity) mContext).finish();
                }
            }
        });
        tvTitle = (TextView) findViewById(R.id.tv_title);
    }

    public void setTitleBarBg(int res) {
        rlTitle.setBackgroundResource(res);
    }

    public void setLeftButton(int imageRes, OnClickListener onClickListener) {
        if (imageRes != 0) {
            leftButton.setImageResource(imageRes);
        }
        leftButton.setOnClickListener(onClickListener);
    }

    public void setLeftButton(OnClickListener onClickListener) {
        leftButton.setOnClickListener(onClickListener);
    }

    public void setRightButton(int imageRes, OnClickListener onClickListener) {
        if (imageRes != 0) {
            rightButton.setImageResource(imageRes);
        }
        rightButton.setOnClickListener(onClickListener);
        rightButton.setVisibility(VISIBLE);
    }

    public void setRightButton(CharSequence btnText, OnClickListener onClickListener) {
        tvRightButton2.setText(btnText);
        tvRightButton2.setOnClickListener(onClickListener);
        tvRightButton2.setVisibility(VISIBLE);
    }

    public TextView getRightTextButton() {
//        tvRightButton2.setText(btnText);
//        tvRightButton2.setOnClickListener(onClickListener);
//        tvRightButton2.setVisibility(VISIBLE);
        return tvRightButton2;
    }


    public void setRightTextButtonEnabled(boolean enabled) {
        tvRightButton2.setEnabled(enabled);
    }

    public void setRightButton2(String btnText, OnClickListener onClickListener) {
        tvRightButton.setText(btnText);
        tvRightButton.setOnClickListener(onClickListener);
        tvRightButton.setVisibility(VISIBLE);
    }

    public void setTitle(CharSequence title) {
        this.tvTitle.setText(title);
    }

    public String getTitle() {
        return this.tvTitle.getText().toString();
    }

    public void showLeftButton(boolean isShow) {
        if (isShow) {
            leftButton.setVisibility(VISIBLE);
        } else {
            leftButton.setVisibility(GONE);
        }
    }

    public void showRedDot(boolean isShow) {
        LogUtils.i("Call showRedDot");
        if (isShow) {
            LogUtils.i("Set redDot Visible");
            redDot.setVisibility(VISIBLE);
        } else {
            LogUtils.i("Set redDot Gone");
            redDot.setVisibility(GONE);
        }
    }

    public void showRightButton(boolean isShow) {
        if (isShow) {
            rightButton.setVisibility(VISIBLE);
        } else {
            rightButton.setVisibility(GONE);
        }
    }

    public void showTvRightButton(boolean isShow) {
        if (isShow) {
            tvRightButton.setVisibility(VISIBLE);
        } else {
            tvRightButton.setVisibility(GONE);
        }
    }

    public ImageView getRedDot() {
        return redDot;
    }

    /**
     * 获取左边ImageView
     */
    public ImageView getleftButton() {
        return leftButton;
    }
}
