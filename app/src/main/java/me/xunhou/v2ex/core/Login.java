package me.xunhou.v2ex.core;

import java.io.IOException;
import java.io.InputStream;

import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.client.Clenit;
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
//    private Bus mBus;

    public Login(){
        api = Clenit.getServiceClient();
//        mBus = BusProvider.getBus();
    }

    public void getOnce(){
        api.getOnce(new Callback<Response>() {
            @Override
            public void success(Response res, Response response2) {
                try {
                    InputStream in = res.getBody().in();
                    String responseString = StringUtil.inputStream2String(in);
                    String once = V2EXPaser.paserOnce(responseString);
                    login("ihgoo","HUKAIJUN123",once);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    public void login(String username,String password,String once){
        api.login("%2F",username,password,once,new Callback<Response>() {
            @Override
            public void success(Response res, Response response) {
                try {
                    InputStream in = res.getBody().in();
                    String responseString = StringUtil.inputStream2String(in);

                    for (Header header : response.getHeaders()) {
                        if (header.getName().equalsIgnoreCase("set-cookie")
                                && header.getValue().toLowerCase().contains("PB3_SESSION") ){
//                            saveSessionCookie(header.getValue());


                            break;
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
