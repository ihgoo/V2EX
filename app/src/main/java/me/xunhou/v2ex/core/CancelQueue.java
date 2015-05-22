package me.xunhou.v2ex.core;

import me.xunhou.v2ex.client.Clenit;

/**
 * Created by ihgoo on 2015/5/21.
 */
public class CancelQueue {


    public void cancel() {
        Clenit.stopAll();
    }
}
