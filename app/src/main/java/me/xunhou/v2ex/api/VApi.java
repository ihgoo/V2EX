package me.xunhou.v2ex.api;

import me.xunhou.v2ex.model.TopicBean;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by ihgoo on 2015/5/18.
 */
public interface VApi {

    @GET("/recent")
    void getTopicsList(@Query("p") int page, Callback<Response> callback);

    @GET("/t/{tid}")
    void getForumDetail(@Path("tid") String tid, Callback<Response> callback);

    @GET("/api/topics/show.json?id={tid}")
    void getForumDetailByApi(@Path("tid") String tid, Callback<TopicBean> callback);

    @GET("/signin")
    void getOnce(Callback<Response> callback);

    @FormUrlEncoded
    @POST("/signin")//next=%2F&u=aa&once=59483&p=aa
    void login(@Field("next") String next, @Field("u") String username, @Field("password") String password, @Field("once") String once,Callback<Response> callback);

}