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
        toast.setAnimations(SuperToast.Animations.POPUP);
        toast.setIcon(com.github.johnpersano.supertoasts.R.drawable.icon_light_info, SuperToast.IconPosition.LEFT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    /**
     * Show Super Toast
     * @param context
     * @param string
     */
    public static void showMediumTop(Context context,String string){
        SuperToast toast = getSuperToast(context, string);
        toast.setDuration(SuperToast.Duration.MEDIUM);
        toast.setAnimations(SuperToast.Animations.FLYIN);
        toast.setIcon(com.github.johnpersano.supertoasts.R.drawable.icon_light_info, SuperToast.IconPosition.LEFT);
        toast.setGravity(Gravity.TOP,0,0);
        toast.show();
    }

    /**
     * show Card Toast
     * 注意You must have a LinearLayout with the id of card_container in your layout!
     * @param activity
     * @param string
     */
    public static void showCardToast(Activity activity,String string){
        SuperCardToast superCardToast = new SuperCardToast(activity, SuperToast.Type.STANDARD);
        superCardToast.setText(string);
        superCardToast.setTextSize(14);
        superCardToast.setBackground(R.color.colorPrimary);
        superCardToast.show();
    }

    /**
     * show Card Toast
     * 注意You must have a LinearLayout with the id of card_container in your layout!
     * @param activity
     * @param string
     * @param color  R.color.colorPrimary
     */
    public static void showCardToast(Activity activity,String string,int color){
        SuperCardToast superCardToast = new SuperCardToast(activity, SuperToast.Type.STANDARD);
        superCardToast.setText(string);
        superCardToast.setTextSize(14);
        superCardToast.setBackground(color);
        superCardToast.show();
    }

    /**
     * Show Super Toast
     * @param context
     * @param string
     */
    public static void showMediumBottom(Context context,String string){
        SuperToast toast = getSuperToast(context, string);
        toast.setDuration(SuperToast.Duration.MEDIUM);
        toast.setAnimations(SuperToast.Animations.POPUP);
        toast.setIcon(com.github.johnpersano.supertoasts.R.drawable.icon_light_info, SuperToast.IconPosition.LEFT);
        toast.setGravity(Gravity.BOTTOM,0,0);
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
        superToast.setAnimations(SuperToast.Animations.SCALE);
        superToast.setDuration(SuperToast.Duration.SHORT);
//        superToast.setBackground(SuperToast.Background.GRAY);
//        superToast.setTextSize(SuperToast.TextSize.SMALL);
        return superToast;
    }



}
