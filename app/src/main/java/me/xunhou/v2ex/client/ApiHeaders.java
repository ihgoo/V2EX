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
        if (sessionId != null) {
//            request.setHeader(...);
        }
    }
}
