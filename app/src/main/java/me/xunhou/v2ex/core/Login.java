package me.xunhou.v2ex.core;

import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;

import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.client.Clenit;
import me.xunhou.v2ex.model.V2EXSettingHelper;
import me.xunhou.v2ex.utils.StringUtil;
import me.xunhou.v2ex.utils.V2EXPaser;
import retrofit.client.Response;
import rx.schedulers.Schedulers;

/**
 * Created by ihgoo on 2015/5/25.
 */
public class Login {

    private VApi api;
    private Handler mHandler;

    public Login(Handler handler) {
        api = Clenit.getServiceClient();
        mHandler = handler;
    }

    public void login(final String username, final String password) {
        api.getOnce()
                .subscribeOn(Schedulers.io())
                .subscribe(response -> {
                    try {
                        InputStream in = response.getBody().in();
                        String responseString = StringUtil.inputStream2String(in);
                        String once = V2EXPaser.paserOnce(responseString);
                        mHandler.sendEmptyMessage(0);
                        loginWithOnce(username, password, once);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }


    public void loginWithOnce(final String username, final String password, String once) {
        api.login("/", username, password, once)
                .subscribeOn(Schedulers.io())
                .subscribe(response -> handleLogin(response, username));
    }

    private void handleLogin(Response response, String username) {
        try {
            InputStream in = response.getBody().in();
            String responseString = StringUtil.inputStream2String(in);
            V2EXSettingHelper.getInstance().setUsername(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
