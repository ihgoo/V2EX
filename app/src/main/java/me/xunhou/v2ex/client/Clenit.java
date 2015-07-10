package me.xunhou.v2ex.client;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.persistence.Constant;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by ihgoo on 2015/5/18.
 */
public class Clenit {
    private Clenit() {
    }

    public static String API_URL = Constant.API_URL;

    public static ExecutorService mExecutorService;

    private static VApi instance;

    public static VApi getServiceClient() {
        if (instance == null) {
            synchronized (Clenit.class) {
                if (instance == null) {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    okHttpClient.setCookieHandler(new MyCookieManager());
                    okHttpClient.setReadTimeout(100, TimeUnit.SECONDS);
                    okHttpClient.setConnectTimeout(100, TimeUnit.SECONDS);

                    RestAdapter.Builder restAdapter = new RestAdapter.Builder();
                    ApiHeaders apiHeaders = new ApiHeaders();
                    restAdapter.setRequestInterceptor(apiHeaders);
                    restAdapter.setEndpoint(API_URL);
                    restAdapter.setClient(new OkClient(okHttpClient));
                    restAdapter.setLogLevel(RestAdapter.LogLevel.FULL);
                    mExecutorService = Executors.newCachedThreadPool();
                    instance = restAdapter
                            .build()
                            .create(VApi.class);
                }
            }
        }
        return instance;
    }

    public static void stopAll() {
        List<Runnable> pendingAndOngoing = mExecutorService.shutdownNow();
    }

}
