package me.xunhou.v2ex.client;

import retrofit.RequestInterceptor;

/**
 * Created by ihgoo on 2015/5/19.
 */
public class ApiHeaders implements RequestInterceptor {
    private String sessionId;

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void clearSessionId() {
        sessionId = null;
    }

    @Override public void intercept(RequestFacade request) {
        request.addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 4.1.1; Nexus 7 Build/JRO03D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166  Safari/535.19");
        request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        if (sessionId != null) {
//            request.setHeader(...);
        }
    }
}
