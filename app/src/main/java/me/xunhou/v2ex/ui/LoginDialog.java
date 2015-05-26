package me.xunhou.v2ex.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.xunhou.v2ex.R;
import me.xunhou.v2ex.core.Login;
import me.xunhou.v2ex.model.NoticeBean;
import me.xunhou.v2ex.utils.ToastUtil;

/**
 * Created by ihgoo on 2015/5/25.
 */
public class LoginDialog extends Dialog {

    @InjectView(R.id.login_username)
    EditText loginUsername;
    @InjectView(R.id.login_password)
    EditText loginPassword;
    @InjectView(R.id.login_btn)
    Button loginBtn;

    Login login;

    private Context mContext;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:


                    break;
                case 1:


                    break;
            }
        }
    };

    public LoginDialog(Context context) {
        super(context);
        login = new Login(mHandler);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);
        ButterKnife.inject(this);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @OnClick(R.id.login_btn)
    void login(){
        login.getOnce();
    }


    @Subscribe
    public void show(NoticeBean noticeBean){
        ToastUtil.showMediumTime(mContext,noticeBean.getContent());
    }

}
