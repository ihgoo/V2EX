package me.xunhou.v2ex.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by ihgoo on 2015/5/26.
 */
public class V2EXSettingHelper {

    public static final String PREF_USERNAME = "PREF_USERNAME";
    public static final String PREF_PASSWORD = "PREF_PASSWORD";
    public static final String PREF_SESSION = "PREF_SESSION";
    public static final String PREF_COOKIE_A2 = "PREF_COOKIE_A2";
    public static final String PREF_ONCE = "PREF_ONCE";


    private Context mContext;
    private SharedPreferences mSharedPreferences;

    private static V2EXSettingHelper mV2EXSettingHelper;

    private V2EXSettingHelper() {
    }

    private static class SingletonHolder {
        public static final V2EXSettingHelper INSTANCE = new V2EXSettingHelper();
    }

    public static V2EXSettingHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public void init(Context context) {
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        reload();
    }

    private void reload() {


    }

    public void setOnce(String once) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(PREF_ONCE, once);
        edit.commit();
    }

    public String getOnce() {
        return mSharedPreferences.getString(PREF_SESSION, "");
    }

    public String getSession() {
        return mSharedPreferences.getString(PREF_SESSION, "");
    }


    public void setSession(String session) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(PREF_SESSION, session);
        edit.commit();
    }

    public String getA2() {
        return mSharedPreferences.getString(PREF_COOKIE_A2, "");
    }


    public void setA2(String session) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(PREF_COOKIE_A2, session);
        edit.commit();
    }

    public String getUsername() {
        return mSharedPreferences.getString(PREF_USERNAME, "未登录");
    }

    public void setUsername(String username) {
        mSharedPreferences.edit().putString(PREF_USERNAME, username).commit();
    }

}
