package com.uikit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.modules.network.NetworkState;
import com.uikit.R;


/**
 * 空界面
 *
 * @author kevin
 * @version v1.0
 * @since 2014-11/18/14
 */
public class EmptyView extends FrameLayout {

    private TextView mTitle;
    private TextView mSecondTitle;
    private ImageView mEmptyViewTop;
    private ImageView mEmptyViewBottom;


    private View mNetworkGroup;
    private TextView mNetworkTitle;
    private TextView mNetworkSecondTitle;
    private ImageView mNetworkEmptyImage;

    private OnClickListener mRefreshListener;

    private boolean mIsCanRefresh = true;
    private boolean mIsCheckNetwork = true;

    public EmptyView(Context context) {
        super(context);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.uikit_view_default_empty, this, true);
        mTitle = (TextView) findViewById(R.id.uikit_empty_title);
        mSecondTitle = (TextView) findViewById(R.id.uikit_empty_second_title);
        mEmptyViewTop = (ImageView) findViewById(R.id.uikit_empty_image_top);
        mEmptyViewBottom = (ImageView) findViewById(R.id.uikit_empty_image_bottom);

        mNetworkGroup = findViewById(R.id.uikit_empty_network_group);
        mNetworkTitle = (TextView) findViewById(R.id.uikit_empty_network_title);
        mNetworkSecondTitle = (TextView) findViewById(R.id.uikit_empty_network_second_title);
        mNetworkEmptyImage = (ImageView) findViewById(R.id.uikit_empty_network_image);

        setBackgroundColor(getResources().getColor(R.color.uikit_grey_light));
    }

    public TextView getSecondTitle() {
        return mSecondTitle;
    }

    public TextView getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setSecondTitle(String title) {
        mSecondTitle.setText(title);
    }

    public void setImageViewResourceIdOfBottom(int resId) {
        mEmptyViewBottom.setImageResource(resId);
    }

    public void setImageViewResourceIdOfTop(int resId) {
        mEmptyViewTop.setImageResource(resId);
    }

    public void setRefreshListener(OnClickListener refreshListener) {
        mRefreshListener = refreshListener;
        if (mIsCanRefresh) {
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mRefreshListener != null) {
//                        setVisibility(GONE);
                        mRefreshListener.onClick(v);
                    }
                }
            });
        } else {
            setOnClickListener(null);
        }
    }

    public void setCanRefresh(boolean canRefresh) {
        mIsCanRefresh = canRefresh;
    }

    @Override
    public void setVisibility(int visibility) {

        if (mIsCheckNetwork) {
            if (!NetworkState.available()) {
                showNetworkFail();
                visibility = VISIBLE;
            }
        }

        super.setVisibility(visibility);
    }

    private void showNetworkFail() {
        mNetworkGroup.setVisibility(VISIBLE);
        mNetworkTitle.setText(R.string.uikit_empty_title);
        if (mIsCanRefresh) {
            mNetworkSecondTitle.setText(R.string.uikit_empty_second_title);
            mNetworkGroup.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mRefreshListener != null) {
                        mNetworkGroup.setVisibility(GONE);
                        setVisibility(GONE);
                        mRefreshListener.onClick(v);
                    }
                }
            });
        } else {
            mNetworkGroup.setOnClickListener(null);
        }
        mNetworkEmptyImage.setImageResource(R.drawable.uikit_pic_empty_network);
    }

    public void checkNetwork(boolean check) {
        mIsCheckNetwork = check;
    }
}
