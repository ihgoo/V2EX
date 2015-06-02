package me.xunhou.v2ex.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import me.xunhou.v2ex.R;

/**
 * Created by ihgoo on 2015/5/27.
 */
public class V2EXProgressDialog extends ProgressDialog {


    public final static int INFO = 0;
    public final static int ERROR = 9;

    public V2EXProgressDialog(Context context) {
        super(context);
        setProgressStyle(V2EXProgressDialog.STYLE_SPINNER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void dismiss(String message){
        dissmiss(message, 1000, INFO);
    }

    public void dissmissError(String message){
        dissmiss(message, 3000, ERROR);
    }

    private void dissmiss(String message,int millisToWait, int status){
        if (message != null)
            setMessage(message);
        if (status == ERROR) {
            setIndeterminateDrawable(new IconicsDrawable(getContext(),
                    GoogleMaterial.Icon.gmd_error).sizeDp(48)
                    .color(getContext().getResources().getColor(R.color.colorPrimary)));
        } else {
            setIndeterminateDrawable(new IconicsDrawable(getContext(),
                    GoogleMaterial.Icon.gmd_info).sizeDp(48)
                    .color(getContext().getResources().getColor(R.color.md_green_300)));
        }
        setIndeterminate(true);
        new CountDownTimer(millisToWait, millisToWait) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                dismiss();
            }
        }.start();


    }

    public static V2EXProgressDialog show(Context context, String message) {
        V2EXProgressDialog progressDialog = new V2EXProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }


    public static V2EXProgressDialog show(Context context, int messageResId) {
        V2EXProgressDialog progressDialog = new V2EXProgressDialog(context);
        progressDialog.setMessage(context.getResources().getString(messageResId));
        progressDialog.show();
        return progressDialog;
    }
}
