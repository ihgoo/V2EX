package me.xunhou.v2ex.api;

import java.util.ArrayList;

import me.xunhou.v2ex.model.ForumItemBean;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by ihgoo on 2015/5/18.
 */
public interface VApi {

    @GET("/api/topics/hot.json")
    void getTopicsList(Callback<ArrayList<ForumItemBean>> callback);

    @GET("/?tab=hot")
    void getHotList(Callback<String> callback);
}
