package me.xunhou.v2ex.core;

import com.squareup.otto.Bus;

import me.xunhou.v2ex.api.VApi;
import me.xunhou.v2ex.client.Clenit;
import me.xunhou.v2ex.utils.BusProvider;

/**
 * Created by ihgoo on 2015/5/27.
 */
public class NewThread  {


    private VApi mVApi;
    private Bus mBus;

    public NewThread() {
        mVApi = Clenit.getServiceClient();
        mBus = BusProvider.getBus();
    }




}
