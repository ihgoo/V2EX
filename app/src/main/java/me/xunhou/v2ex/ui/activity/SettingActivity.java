package me.xunhou.v2ex.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import me.xunhou.v2ex.model.V2EXSettingHelper;
import me.xunhou.v2ex.ui.LoginDialog;
import me.xunhou.v2ex.ui.V2EXProgressDialog;

/**
 * Created by ihgoo on 2015/5/22.
 */
public class SettingActivity extends Activity {

    private Context mContext = this;
    private V2EXProgressDialog dialog;
    LoginDialog loginDialog;


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    loginDialog.dismiss();
                    dialog = V2EXProgressDialog.show(mContext, "正在登录...");

                    break;
                case 1:
                    dialog.dismiss();

                    break;
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        V2EXSettingHelper settingHelper = V2EXSettingHelper.getInstance();
        String username = settingHelper.getUsername();
    }


//    @Override
//    public void onClick(TextItem item) {
//        String name = item.getName();
//        if (name.equals("username")){
//            loginDialog = new LoginDialog(SettingActivity.this);
//            loginDialog.setHander(mHandler);
//            loginDialog.show();
//        }
//    }
}
