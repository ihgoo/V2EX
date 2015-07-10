package me.xunhou.v2ex.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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
    private Handler mHandler;


    public void setHander(Handler hander) {
        mHandler = hander;
        login = new Login(mHandler);
    }

    public LoginDialog(Context context) {
        super(context);
        mContext = context;
    }

    public LoginDialog(Context context, int theme) {
        super(context, theme);
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
    void login() {
        String username = loginUsername.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(username)&& !TextUtils.isEmpty(password)){
            login.login(username,password);
        }else {
            ToastUtil.showShortTime(mContext,"帐号和密码不能为空");
        }

    }


    ProgressDialog mProgressDialog;

    @Subscribe
    public void show(NoticeBean noticeBean) {
        ToastUtil.showMediumTime(mContext, noticeBean.getContent());


    }

}
