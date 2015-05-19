package me.xunhou.v2ex.utils;

import android.content.Context;

import com.github.johnpersano.supertoasts.SuperToast;


/**
 * Created by ihgoo on 2015/1/22.
 */
public class ToastUtil {


    /**
     * Show Super Toast
     * @param context
     * @param string
     */
    public static void showShortTime(Context context,String string){
        SuperToast toast = getSuperToast(context, string);
        toast.setDuration(SuperToast.Duration.SHORT);
        toast.show();
    }

    /**
     * Show Super Toast
     * @param context
     * @param string
     */
    public static void showLongTime(Context context,String string){
        SuperToast toast = getSuperToast(context, string);
        toast.setDuration(SuperToast.Duration.LONG);
        toast.show();
    }


    /**
     * Show Super Toast
     * @param context
     * @param string
     */
    public static void showLongTime(Context context,int string){
        SuperToast toast = getSuperToast(context, context.getString(string));
        toast.setDuration(SuperToast.Duration.LONG);
        toast.show();
    }

    /**
     * Show Super Toast
     * @param context
     * @param string
     */
    public static void showMediumTime(Context context,String string){
        SuperToast toast = getSuperToast(context, string);
        toast.setDuration(SuperToast.Duration.MEDIUM);
        toast.show();
    }

    /**
     * Get SuperToast.A toast can running outside of Activity.
     * @param context
     * @param string
     * @return
     */
    private static SuperToast getSuperToast(Context context, String string){
        SuperToast superToast = new SuperToast(context);
        superToast.cancelAllSuperToasts();
        superToast.setText(string);
        superToast.setAnimations(SuperToast.Animations.POPUP);
        superToast.setDuration(SuperToast.Duration.SHORT);
        superToast.setBackground(SuperToast.Background.GRAY);
        superToast.setTextSize(SuperToast.TextSize.SMALL);
        return superToast;
    }



}
