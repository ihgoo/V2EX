package me.xunhou.v2ex.client;

import retrofit.RequestInterceptor;

/**
 * Created by ihgoo on 2015/5/19.
 */
public class ApiHeaders implements RequestInterceptor {

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.1.1; zh-cn; HTC One X - 4.1.1 - API 16 - 720x1280 Build/JRO03S) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
        request.addHeader("Accept", "*/*");
        request.addHeader("Accept-Language", "zh-cn,zh");
    }
}
