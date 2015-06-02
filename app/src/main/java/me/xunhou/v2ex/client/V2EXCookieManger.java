package me.xunhou.v2ex.client;

import java.io.IOException;
import java.net.CookieManager;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by ihgoo on 2015/6/1.
 */
public class V2EXCookieManger extends CookieManager {


    @Override
    public void put(URI uri, Map<String, List<String>> responseHeaders) throws IOException {
        super.put(uri, responseHeaders);
    }
}
