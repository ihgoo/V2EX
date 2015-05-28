package me.xunhou.v2ex.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import me.xunhou.v2ex.R;

/**
 * Created by ihgoo on 2015/5/18.
 */
public class MainActivity extends AppCompatActivity {

    private AccountHeader headerResult;
    private int mQuit = 0;
    private OnSwipeTouchListener mSwipeListener;
    Drawer drawerResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_main_page);
        ButterKnife.inject(this);

        setupDrawer();

        mSwipeListener = new OnSwipeTouchListener(this) {
            public void onSwipeRight() {
//                if (HiSettingsHelper.getInstance().isGestureBack()
//                        && !HiSettingsHelper.getInstance().getIsLandscape()
//                        && !(getFragmentManager().findFragmentByTag(PostFragment.class.getName()) instanceof PostFragment)) {
                    popFragment(false);
//                }
            }
        };

        findViewById(R.id.main_frame_container).setOnTouchListener(mSwipeListener);




        getFragmentManager().addOnBackStackChangedListener(new BackStackChangedListener());
        Fragment fragment = new ForumListFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                .commit();
    }


    private void setupDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setNavigationIcon(null);
        setSupportActionBar(toolbar);


        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.bg_drawer_header)
                .withCompactStyle(true)
                .withSelectionListEnabled(false)
                .addProfiles(new ProfileDrawerItem()
                        .withEmail("xunhou")
                        .withIcon("https://cdn.v2ex.co/gravatar/01edd61225f7b051d8874a86dcee87de?s=48&d=retro"))
                .build();


        ArrayList<IDrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_search).withIcon(GoogleMaterial.Icon.gmd_search));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_mypost).withIcon(GoogleMaterial.Icon.gmd_grade));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_myreply).withIcon(GoogleMaterial.Icon.gmd_forum));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_subject_favorites).withIcon(GoogleMaterial.Icon.gmd_favorite));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_node_favorites).withIcon(GoogleMaterial.Icon.gmd_mail).withBadgeTextColor(Color.RED));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_notify).withIcon(GoogleMaterial.Icon.gmd_notifications).withBadgeTextColor(Color.RED));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_setting)
                .withIcon(GoogleMaterial.Icon.gmd_settings));


        drawerResult = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(true)
                .withDrawerItems(drawerItems)
                .withOnDrawerItemClickListener(new DrawerItemClickListener())
                .build();

        drawerResult.keyboardSupportEnabled(this, true);
        drawerResult.getListView().setVerticalScrollBarEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                popFragment(false);
            }
        });
    }




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
                Fragment fg = fm.findFragmentById(R.id.main_frame_container);
                if (fg != null) {
                    fg.setHasOptionsMenu(true);
                }
            }
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void onBackPressed() {
        if (!popFragment(true)) {
            mQuit++;
            if (mQuit == 1) {
                Toast.makeText(this, "再按一次退出V2EX", Toast.LENGTH_LONG).show();
            } else {
                finish();
            }
        }
    }


    private class BackStackChangedListener implements FragmentManager.OnBackStackChangedListener {

        @Override
        public void onBackStackChanged() {
            mQuit = 0;

            FragmentManager fm = getFragmentManager();

            if (fm.getBackStackEntryCount() > 0) {
                if (getSupportActionBar() != null)
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } else {
                if (getSupportActionBar() != null)
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }

    }

    private class DrawerItemClickListener implements Drawer.OnDrawerItemClickListener {
        @Override
        public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
            return false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (HiSettingsHelper.getInstance().isGestureBack()) {
            mSwipeListener.onTouch(null, ev);
//        }
        return super.dispatchTouchEvent(ev);
    }


}
