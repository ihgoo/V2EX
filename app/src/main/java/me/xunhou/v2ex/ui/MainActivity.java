package me.xunhou.v2ex.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import me.xunhou.v2ex.R;
import me.xunhou.v2ex.persistence.IntentConstant;

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
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstant.NODE,"recent");
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                .commit();
    }


    private void setupDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);


//        headerResult = new AccountHeaderBuilder()
//                .withActivity(this)
//                .withHeaderBackground(R.drawable.bg_drawer_header)
//                .withCompactStyle(true)
//                .withSelectionListEnabled(false)
//                .addProfiles(new ProfileDrawerItem()
//                        .withName("xunhou")
//                        .withIcon("http://v1.qzone.cc/avatar/201305/17/22/59/519645d5ed855399.jpg!200x200.jpg"))
//                .build();


        ArrayList<IDrawerItem> drawerItems = new ArrayList<>();
//        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_search).withIcon(GoogleMaterial.Icon.gmd_search));
//        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_mypost).withIcon(GoogleMaterial.Icon.gmd_grade));
//        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_myreply).withIcon(GoogleMaterial.Icon.gmd_forum));
//        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_subject_favorites).withIcon(GoogleMaterial.Icon.gmd_favorite));
//        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_node_favorites).withIcon(GoogleMaterial.Icon.gmd_mail).withBadgeTextColor(Color.RED));
//        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_notify).withIcon(GoogleMaterial.Icon.gmd_notifications).withBadgeTextColor(Color.RED));
//        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_setting)
//                .withIcon(GoogleMaterial.Icon.gmd_settings));

        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_all)
                .withIcon(GoogleMaterial.Icon.gmd_settings));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_creative).withIcon(GoogleMaterial.Icon.gmd_search));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_tech).withIcon(GoogleMaterial.Icon.gmd_grade));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_Apple).withIcon(GoogleMaterial.Icon.gmd_forum));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_jobs).withIcon(GoogleMaterial.Icon.gmd_favorite));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_deals).withIcon(GoogleMaterial.Icon.gmd_mail).withBadgeTextColor(Color.RED));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_hot).withIcon(GoogleMaterial.Icon.gmd_notifications).withBadgeTextColor(Color.RED));





        //创意
        //技术
        //好玩
        //Apple
        //酷工作
        //交易
        //城市
        //最热
        //全部


        drawerResult = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                       .withSliderBackgroundColorRes(R.color.white_t)
//                .withAccountHeader(headerResult)
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
            if (!backPressed) {
                if (drawerResult.isDrawerOpen())
                    drawerResult.closeDrawer();
                else
                    drawerResult.openDrawer();
            }
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
                drawerResult.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
                if (getSupportActionBar() != null)
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } else {
                if (getSupportActionBar() != null)
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                drawerResult.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
            }


            if (fm.getBackStackEntryCount() > 0) {
                drawerResult.getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } else {
                drawerResult.getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        }

    }

    private class DrawerItemClickListener implements Drawer.OnDrawerItemClickListener {
        @Override
        public boolean onItemClick(AdapterView<?> adapterView, View view, int position, long l, IDrawerItem iDrawerItem) {

//            Intent intentSetting = new Intent();
//            intentSetting.setClass(MainActivity.this,SettingActivity.class);
//            startActivity(intentSetting);

            Log.e("","position:"+position);
            Fragment fragment = new ForumListFragment();
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    bundle.putBoolean(IntentConstant.NODE_MODE, false);
                    bundle.putString(IntentConstant.NODE,"recent");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case 1:
                    bundle.putBoolean(IntentConstant.NODE_MODE, true);
                    bundle.putString(IntentConstant.NODE,"creative");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case 2:
                    bundle.putBoolean(IntentConstant.NODE_MODE, true);
                    bundle.putString(IntentConstant.NODE, "tech");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case 3:
                    bundle.putBoolean(IntentConstant.NODE_MODE, true);
                    bundle.putString(IntentConstant.NODE, "apple");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case 4:
                    bundle.putBoolean(IntentConstant.NODE_MODE, true);
                    bundle.putString(IntentConstant.NODE, "jobs");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case 5:
                    bundle.putBoolean(IntentConstant.NODE_MODE, true);
                    bundle.putString(IntentConstant.NODE, "deals");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case 6:
                    bundle.putBoolean(IntentConstant.NODE_MODE, true);
                    bundle.putString(IntentConstant.NODE, "hot");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
            }



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
