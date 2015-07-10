package me.xunhou.v2ex.client;

import android.util.Log;

import java.io.IOException;
import java.net.CookieManager;
import java.net.URI;
import java.util.List;
import java.util.Map;

import me.xunhou.v2ex.model.V2EXSettingHelper;

/**
 * Created by ihgoo on 2015/7/10.
 */
public class MyCookieManager extends CookieManager {

    @Override
    public void put(URI uri, Map<String, List<String>> stringListMap) throws IOException {
        super.put(uri, stringListMap);
        if (stringListMap != null && stringListMap.get("Set-Cookie") != null)
            for (String string : stringListMap.get("Set-Cookie")) {
                String cookieValue = string.substring(0, string.indexOf("\"; ") + 1);
                if (string.contains("A2=\"")) {
                    V2EXSettingHelper.getInstance().setA2(cookieValue);
                    Log.e("MyCookieManager", "A2 is " + cookieValue);
                }
                if (string.contains("PB3_SESSION=\"")) {
                    V2EXSettingHelper.getInstance().setSession(cookieValue);
                    Log.e("MyCookieManager", "pb3_session is " + cookieValue);
                }
            }
    }
}