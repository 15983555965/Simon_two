package com.languagehelper.ui.home.fragment;

import android.view.View;
import android.widget.TextView;

import com.languagehelper.R;
import com.languagehelper.base.MFragment;
import com.languagehelper.web_code.RedPacketHelp;

import java.util.Random;

/**创建房间
 * Created by Administrator on 2017/3/1.
 */

public class CreateFragment extends MFragment {

    private TextView mTestView;
    public double d=10;

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.fragment_create);
        mTestView = (TextView) findViewById(R.id.tv_test);

        show();
        mTestView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
    }

    private void show() {
        double[] doubles = RedPacketHelp.clipRedPacket(d, 5);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<doubles.length;i++){
            stringBuilder.append(doubles[i]+"\n");
        }
        mTestView.setText(stringBuilder.toString());
    }
}
