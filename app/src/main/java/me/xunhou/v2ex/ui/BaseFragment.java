package me.xunhou.v2ex.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ihgoo on 2015/5/21.
 */
public class BaseFragment extends Fragment {

    public Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
