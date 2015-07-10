package me.xunhou.v2ex.core;

import com.squareup.otto.Bus;

import java.io.IOException;
import java.io.InputStream;

import me.xunhou.v2ex.R;
import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.client.Clenit;
import me.xunhou.v2ex.model.Message;
import me.xunhou.v2ex.model.TopicBean;
import me.xunhou.v2ex.persistence.Constant;
import me.xunhou.v2ex.utils.BusProvider;
import me.xunhou.v2ex.utils.StringUtil;
import me.xunhou.v2ex.utils.V2EXPaser;
import retrofit.client.Response;
import rx.schedulers.Schedulers;

/**
 * Created by ihgoo on 2015/5/21.
 */
public class ForumDetail extends CancelQueue {

    private VApi mVApi;
    private Bus mBus;

    public ForumDetail() {
        mVApi = Clenit.getServiceClient();
        mBus = BusProvider.getBus();
    }

    public void getForumDetail(String tid) {
        mVApi.getForumDetail(tid)
                .subscribeOn(Schedulers.computation())
                .subscribe(response -> handleForumDetail(response), error -> handleFailure(error));
    }

    private void handleFailure(Throwable error) {
        Message message = new Message();
        message.setReason(R.string.reson_network_error);
        message.setWhat(Constant.RESONSE_NETWORK_ERROR);
        mBus.post(message);

    }

    private void handleForumDetail(Response response) {
        try {
            InputStream in = response.getBody().in();
            String responseString = StringUtil.inputStream2String(in);
            TopicBean topicBean = V2EXPaser.paserFourmDetail(responseString);
            if (topicBean == null) {
                Message message = new Message();
                message.setReason(R.string.reason_need_login);
                message.setWhat(Constant.RESONSE_NEED_LOGIN);
                mBus.post(message);
            } else {
                mBus.post(topicBean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
