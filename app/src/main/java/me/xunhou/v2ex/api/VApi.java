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
    @GET("/go/{node}")
    void getTopicsList(@Query("p") int page, @Path("node") String node, Callback<Response> callback);

    @GET("/t/{tid}")
    void getForumDetail(@Path("tid") String tid, Callback<Response> callback);

    @GET("/api/topics/show.json?id={tid}")
    void getForumDetailByApi(@Path("tid") String tid, Callback<TopicBean> callback);

    @GET("/signin")
    void getOnce(Callback<Response> callback);

    @FormUrlEncoded
    @POST("/signin")
    void login(@Field("next") String next, @Field("u") String username, @Field("password") String password, @Field("once") String once, Callback<Response> callback);

    @FormUrlEncoded
    @POST("/new")
    void newThread(@Field("titile") String title, @Field("content") String content, @Field("node_name") String nodeName, @Field("once") String once, Callback<Response> callback);

    @GET("/new")
    void getOnceByThread(Callback<Response> callback);


    @POST("/t/{uid}")
    void postReply(@Path("uid") String uid, @Field("content") String content, @Field("once") String once, Callback<Response> callback);
}