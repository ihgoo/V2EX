package me.xunhou.v2ex.ui;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by ihgoo on 2015/5/25.
 */
public class LoginDialog extends Dialog {


    public LoginDialog(Context context) {
        super(context);
    }

    public LoginDialog(Context context, int theme) {
        super(context, theme);
    }

    protected LoginDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
