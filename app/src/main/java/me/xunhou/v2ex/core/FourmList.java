package me.xunhou.v2ex.core;

import com.squareup.otto.Bus;

import java.util.List;

import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.client.Clenit;
import me.xunhou.v2ex.model.ForumItemBean;
import me.xunhou.v2ex.utils.BusProvider;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ihgoo on 2015/5/19.
 */
public class FourmList {

    private VApi mVApi;
    private Bus mBus;

    public FourmList(){
        mVApi = Clenit.getServiceClient();
        mBus = BusProvider.getBus();
    }

    public void getTopicsList(){
        mVApi.getTopicsList(new Callback<List<ForumItemBean>>() {
            @Override
            public void success(List<ForumItemBean> list, Response response) {
                mBus.post(list);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
