package me.xunhou.v2ex.core;

import com.squareup.otto.Bus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.client.Clenit;
import me.xunhou.v2ex.model.ForumItemBean;
import me.xunhou.v2ex.paser.PaserFourmList;
import me.xunhou.v2ex.utils.BusProvider;
import me.xunhou.v2ex.utils.StringUtil;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ihgoo on 2015/5/19.
 */
public class FourmList {

    private VApi mVApi;
    private Bus mBus;

    public FourmList() {
        mVApi = Clenit.getServiceClient();
        mBus = BusProvider.getBus();
    }

    public void getTopicsList(int page) {
        mVApi.getTopicsList(page, new Callback<Response>() {
            @Override
            public void success(Response res, Response response) {
                try {
                    InputStream in = res.getBody().in();
                    String responseString = StringUtil.inputStream2String(in);
                    ArrayList<ForumItemBean> list = PaserFourmList.paser2ForumItem(responseString);
                    mBus.post(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mBus.post("获取列表失败");
            }
        });
    }
}
