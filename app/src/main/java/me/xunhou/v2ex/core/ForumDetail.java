package me.xunhou.v2ex.core;

import com.squareup.otto.Bus;

import java.io.IOException;
import java.io.InputStream;

import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.client.Clenit;
import me.xunhou.v2ex.model.Message;
import me.xunhou.v2ex.model.TopicBean;
import me.xunhou.v2ex.utils.BusProvider;
import me.xunhou.v2ex.utils.StringUtil;
import me.xunhou.v2ex.utils.V2EXPaser;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ihgoo on 2015/5/21.
 */
public class ForumDetail extends CancelQueue{

    private VApi mVApi;
    private Bus mBus;

    public ForumDetail() {
        mVApi = Clenit.getServiceClient();
        mBus = BusProvider.getBus();
    }

    public void getForumDetail(String tid) {
        mVApi.getForumDetail(tid,new Callback<Response>() {
            @Override
            public void success(Response res, Response response) {
                try {
                    InputStream in = res.getBody().in();
                    String responseString = StringUtil.inputStream2String(in);
                    TopicBean topicBean = V2EXPaser.paserFourmDetail(responseString);
                    if (topicBean==null){
                        Message message = new Message();
                        message.setReason("需要登录...");
                        message.setWhat(1);
                        mBus.post(message);
                    }else {
                        mBus.post(topicBean);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Message message = new Message();
                message.setReason("获取帖子详情失败...");
                message.setWhat(1);
                mBus.post(message);
            }
        });
    }


}
