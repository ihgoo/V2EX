package me.xunhou.v2ex.core;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.client.Clenit;
import me.xunhou.v2ex.model.V2EXSettingHelper;
import me.xunhou.v2ex.utils.StringUtil;
import me.xunhou.v2ex.utils.V2EXPaser;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

/**
 * Created by ihgoo on 2015/5/25.
 */
public class Login {

    private VApi api ;
    private Handler mHandler;

    public Login(Handler handler){
        api = Clenit.getServiceClient();
        mHandler = handler;
    }

    public void getOnce(){
        api.getOnce(new Callback<Response>() {
            @Override
            public void success(Response res, Response response2) {
                try {
                    InputStream in = res.getBody().in();
                    String responseString = StringUtil.inputStream2String(in);
                    String once = V2EXPaser.paserOnce(responseString);
                    mHandler.sendEmptyMessage(0);

                    login("ihgoo", "HUKAIJUN123", once);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    public void login(final String username,String password,String once){
        api.login("%2F",username,password,once,new Callback<Response>() {
            @Override
            public void success(Response res, Response response) {
                try {
                    InputStream in = res.getBody().in();
                    String responseString = StringUtil.inputStream2String(in);

                    String cookie  = null;
                    for (Header header : response.getHeaders()) {
                        if (header.getName().equalsIgnoreCase("set-cookie")
                                && header.getValue().toLowerCase().contains("pb3_session") ){
                            Log.e("pb3_session","pb3_session is "+header.getValue());

                            cookie = header.getValue();
                            V2EXSettingHelper.getInstance().setSession(header.getValue());
                            V2EXSettingHelper.getInstance().setUsername(username);
//                            mHandler.sendEmptyMessage(1);
                            break;
                        }
                        if (header.getName().equalsIgnoreCase("set-cookie")
                                && header.getValue().toLowerCase().contains("a2") ){
                            Log.e("a2","a2 is "+header.getValue());


                            V2EXSettingHelper.getInstance().setA2(header.getValue());
                            V2EXSettingHelper.getInstance().setUsername(username);
//                            mHandler.sendEmptyMessage(1);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

}
