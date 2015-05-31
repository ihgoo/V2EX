package me.xunhou.v2ex.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;

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



    void setActionBarTitle(CharSequence title) {
        if (getActivity() != null) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
                actionBar.setTitle(title);
        }
    }

    void setActionBarTitle(@StringRes int resId) {
        if (getActivity() != null) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null)
                actionBar.setTitle(resId);
        }
    }

//    void setActionBarDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
//        if (getActivity() != null) {
//            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
//            if (actionBar != null)
//                actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
//        }
//    }
//
    void syncActionBarState() {
        if (getActivity() != null) {
            Drawer drawerResult = ((MainActivity) getActivity()).drawerResult;
            if (drawerResult != null)
                drawerResult.getActionBarDrawerToggle().syncState();
        }
    }
//
    void setDrawerSelection(int identifier) {
        //this only set DrawerItem, not StickyDrawerItem
        try {
            if (getActivity() != null) {
                Drawer drawerResult = ((MainActivity) getActivity()).drawerResult;
                if (drawerResult != null
                        && !drawerResult.isDrawerOpen()
                        && drawerResult.getPositionFromIdentifier(identifier) != drawerResult.getCurrentSelection())
                    drawerResult.setSelectionByIdentifier(identifier, false);
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
