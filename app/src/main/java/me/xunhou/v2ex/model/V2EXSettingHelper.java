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


    public String getSession(){
        return mSharedPreferences.getString(PREF_SESSION, "");
    }


    public void setSession(String session){
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(PREF_SESSION,session);
        edit.commit();
    }

    public String getUsername(){
        return mSharedPreferences.getString(PREF_USERNAME, "未登录");
    }

    public void setUsername(String username){
        mSharedPreferences.edit().putString(PREF_USERNAME,username).commit();
    }

}
