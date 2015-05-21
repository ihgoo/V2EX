package me.xunhou.v2ex.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by ihgoo on 2015/5/18.
 */
public interface VApi {

    @GET("/recent")
    void getTopicsList(@Query("p") int page,Callback<Response> callback);

    @GET("/?tab=hot")
    void getHotList(Callback<String> callback);
}
