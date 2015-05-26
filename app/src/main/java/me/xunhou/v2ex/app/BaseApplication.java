package me.xunhou.v2ex.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.SparseArray;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.lang.ref.WeakReference;

import me.xunhou.v2ex.model.V2EXSettingHelper;

/**
 * Created by ihgoo on 2015/1/19.
 */
public class BaseApplication extends Application {

    static Context instance;

    @Override
    public void onCreate() {
        instance = getApplicationContext();
        Fresco.initialize(this);
        super.onCreate();
        V2EXSettingHelper.getInstance().init(this);
        CrashHandler.getInstance().init(this);
    }



    public static WeakReference<Activity> instanceRef;

    public static synchronized Context getInstance() {
        if (instanceRef == null || instanceRef.get() == null) {
            return BaseApplication.getContext();
        } else {
            return instanceRef.get();
        }
    }

    public static synchronized Activity getActivity() {
        Activity result = null;
        if (instanceRef != null && instanceRef.get() != null) {
            result = instanceRef.get();
        }
        return result;
    }

    public static synchronized Context getContext() {
        return instance;
    }

    public static SparseArray<WeakReference<Activity>> taskStack = new SparseArray<WeakReference<Activity>>();

    public static synchronized SparseArray<WeakReference<Activity>> getTaskStack() {
        return taskStack;
    }


}
