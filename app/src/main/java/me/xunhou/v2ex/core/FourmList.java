package me.xunhou.v2ex.core;

import com.squareup.otto.Bus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.client.Clenit;
import me.xunhou.v2ex.model.ForumItemBean;
import me.xunhou.v2ex.utils.BusProvider;
import me.xunhou.v2ex.utils.StringUtil;
import me.xunhou.v2ex.utils.V2EXPaser;
import retrofit.client.Response;
import rx.schedulers.Schedulers;

/**
 * Created by ihgoo on 2015/5/19.
 */
public class FourmList extends CancelQueue {

    private VApi mVApi;
    private Bus mBus;

    public FourmList() {
        mVApi = Clenit.getServiceClient();
        mBus = BusProvider.getBus();
    }

    public void getTopicsList(int page, String node) {
        mVApi.getTopicsList(page, node)
                .subscribeOn(Schedulers.computation())
                .subscribe(response -> handleTopicsList(response), error -> handleFailure(error));
    }

    private void handleTopicsList(Response response) {
        try {
            InputStream in = response.getBody().in();
            String responseString = StringUtil.inputStream2String(in);
            ArrayList<ForumItemBean> list = V2EXPaser.paser2ForumItem(responseString);
            mBus.post(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleFailure(Throwable error) {
        mBus.post("获取列表失败");
    }

}
