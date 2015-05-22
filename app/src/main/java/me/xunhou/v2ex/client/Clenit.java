package me.xunhou.v2ex.client;

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.paser.PaserFourmDetail;
import me.xunhou.v2ex.persistence.Constant;
import me.xunhou.v2ex.utils.StringUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by ihgoo on 2015/5/18.
 */
public class Clenit {

    public static String API_URL = Constant.API_URL;

    public static ExecutorService mExecutorService;


    public static void main(String arg[]) {

        VApi vApi = getServiceClient();
        vApi.getForumDetail("192495",new Callback<Response>() {
            @Override
            public void success(Response res, Response response) {
                try {
                    InputStream in = res.getBody().in();
                    String responseString = StringUtil.inputStream2String(in);
                    PaserFourmDetail.paserFourmDetail(responseString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    public static VApi getServiceClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(100, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(100, TimeUnit.SECONDS);

        RestAdapter.Builder restAdapter = new RestAdapter.Builder();
        ApiHeaders apiHeaders = new ApiHeaders();
        restAdapter.setRequestInterceptor(apiHeaders);

        restAdapter.setEndpoint(API_URL);
        restAdapter.setClient(new OkClient(okHttpClient));
        restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
        mExecutorService = Executors.newCachedThreadPool();
        return restAdapter
                .build()
                .create(VApi.class);
    }

    public static void stopAll() {
        List<Runnable> pendingAndOngoing = mExecutorService.shutdownNow();
    }

}
