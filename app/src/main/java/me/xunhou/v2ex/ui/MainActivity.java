package me.xunhou.v2ex.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.xunhou.v2ex.R;

/**
 * Created by ihgoo on 2015/5/18.
 */
public class MainActivity extends Activity {

    @InjectView(R.id.content_frame)
    FrameLayout contentFrame;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @InjectView(R.id.left_drawer)
    LinearLayout leftDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_main_page);
        ButterKnife.inject(this);


        Log.e("","........");
        getFragmentManager().addOnBackStackChangedListener(new BackStackChangedListener());
        Fragment fragment = new ForumListFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment, ForumListFragment.class.getName())
                .commit();



        drawerLayout.closeDrawer(leftDrawer);
    }




    private long mQuit = 0;



    public boolean popFragment(boolean backPressed) {
        FragmentManager fm = getFragmentManager();
        int count = fm.getBackStackEntryCount();
        if (count > 0) {
            fm.popBackStackImmediate();
            count = fm.getBackStackEntryCount();
            if (count > 0) {
                FragmentManager.BackStackEntry backEntry = getFragmentManager().getBackStackEntryAt(count - 1);
                String str = backEntry.getName();
                Fragment fragment = getFragmentManager().findFragmentByTag(str);

                if (fragment != null) {
                    fragment.setHasOptionsMenu(true);
                }
            } else {
                Fragment fg = fm.findFragmentById(R.id.content_frame);
                if (fg != null) {
                    fg.setHasOptionsMenu(true);
                }
            }
            return true;
        } else {
//            if (!backPressed) {
//                if (drawerResult.isDrawerOpen())
//                    drawerResult.closeDrawer();
//                else
//                    drawerResult.openDrawer();
//            }
            return false;
        }

    }



    private class BackStackChangedListener implements FragmentManager.OnBackStackChangedListener {

        @Override
        public void onBackStackChanged() {
//            mQuit = 0;
//
//            FragmentManager fm = getFragmentManager();
//
//            if (fm.getBackStackEntryCount() > 0) {
//                drawerResult.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
//                if (getSupportActionBar() != null)
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            } else {
//                if (getSupportActionBar() != null)
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                drawerResult.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
//            }
//
//            Logger.v("getBackStackEntryCount = " + String.valueOf(fm.getBackStackEntryCount()));
        }

    }
}
