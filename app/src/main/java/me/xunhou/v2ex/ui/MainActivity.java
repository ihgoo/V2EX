package me.xunhou.v2ex.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.FrameLayout;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.xunhou.v2ex.R;

/**
 * Created by ihgoo on 2015/5/18.
 */
public class MainActivity extends Activity {

    @InjectView(R.id.content_frame)
    FrameLayout contentFrame;
    @InjectView(R.id.left_drawer)
    ListView leftDrawer;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_main_page);
        ButterKnife.inject(this);
        selectItem(0);
    }


    private void selectItem(int position) {
        Fragment fragment = new ForumListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

}
