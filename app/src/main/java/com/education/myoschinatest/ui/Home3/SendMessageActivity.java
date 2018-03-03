package com.education.myoschinatest.ui.Home3;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.education.myoschinatest.R;
import com.education.myoschinatest.base.BaseActivity;
import com.education.myoschinatest.utils.ToastHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.PushListener;

public class SendMessageActivity extends BaseActivity {

    @BindView(R.id.edt_send_message_activity) EditText edtSendMessage;
    @BindView(R.id.ll_send_message_activity) LinearLayout llSendMessage;

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_send_message_activity})
    public void onSendMessageClicked(View view) {
        message = edtSendMessage.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            ToastHelper.showShortMessage("请输入公告内容后再点击发送公告");
        } else {
            BmobPushManager bmobPushManager = new BmobPushManager();
            bmobPushManager.pushMessageAll("新的公告：" + message, new PushListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Log.i("bmob", "Client 推送成功！");
                        ToastHelper.showShortMessage("公告发布成功");
                        finish();
                    } else {
                        Log.i("bmob", "Client 异常：" + e.getMessage());
                        ToastHelper.showShortMessage("公告发布失败 原因：" + e.getMessage());
                    }
                }
            });

        }

    }
}
