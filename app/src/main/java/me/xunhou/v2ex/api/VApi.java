package me.xunhou.v2ex.api;

import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by ihgoo on 2015/5/18.
 */
public interface VApi {
    @GET("/go/{node}")
    Observable<Response> getTopicsList(@Query("p") int page, @Path("node") String node);

    @GET("/t/{tid}")
    Observable<Response> getForumDetail(@Path("tid") String tid);

    @GET("/api/topics/show.json?id={tid}")
    Observable<Response> getForumDetailByApi(@Path("tid") String tid);

    @GET("/signin")
    Observable<Response> getOnce();

    @FormUrlEncoded
    @POST("/signin")
    Observable<Response> login(@Field("next") String next, @Field("u") String username, @Field("password") String password, @Field("once") String once);

    @FormUrlEncoded
    @POST("/new")
    Observable<Response> newThread(@Field("titile") String title, @Field("content") String content, @Field("node_name") String nodeName, @Field("once") String once);

    @GET("/new")
    Observable<Response> getOnceByThread();

    @POST("/t/{uid}")
    Observable<Response> postReply(@Path("uid") String uid, @Field("content") String content, @Field("once") String once);
}