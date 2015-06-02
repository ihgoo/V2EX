package me.xunhou.v2ex.core;

import com.squareup.otto.Bus;

import java.io.IOException;
import java.io.InputStream;

import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.client.Clenit;
import me.xunhou.v2ex.utils.BusProvider;
import me.xunhou.v2ex.utils.StringUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ihgoo on 2015/6/1.
 */
public class ReplyThread extends CancelQueue {

    private VApi mVApi;
    private Bus mBus;

    public ReplyThread() {
        mVApi = Clenit.getServiceClient();
        mBus = BusProvider.getBus();
    }


    public void postReply(String tid,String content,String once) {

        mVApi.postReply("", "", "", new Callback<Response>() {
            @Override
            public void success(Response res, Response response2) {
                try {
                    InputStream in = res.getBody().in();
                    String responseString = StringUtil.inputStream2String(in);
//                    TopicBean topicBean = V2EXPaser.paserFourmDetail(responseString);
//                    mBus.post(topicBean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post("回帖失败..");
            }
        });
    }




}
