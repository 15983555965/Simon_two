package com.languagehelper.ui.home.lobby.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.languagehelper.R;

/**
 * Created by Administrator on 2017/3/4.
 */

public class RoomItemView extends RelativeLayout implements View.OnClickListener {

    private final Context mContext;
    private ImageView mImgView;
    private RoomModel data;

    public RoomItemView(Context context) {
        this(context, null);
    }

    public RoomItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoomItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
        initView();
    }

    private void init() {

    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_item_room, this);
        mImgView = (ImageView) findViewById(R.id.iv_room_item_img);
        mImgView.setOnClickListener(this);
    }

    public void setData(RoomModel data){
        this.data=data;
        update();
    }

    private void update() {
        if (data!=null){
            mImgView.setImageResource(data.getResId());
        }
    }

    @Override
    public void onClick(View view) {

    }

    class RoomModel{
        private int resId;

        public int getResId() {
            return resId;
        }

        public void setResId(int resId) {
            this.resId = resId;
        }
    }
}
