package me.xunhou.v2ex.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;

import butterknife.ButterKnife;
import me.xunhou.v2ex.R;
import me.xunhou.v2ex.persistence.IntentConstant;
import me.xunhou.v2ex.ui.OnSwipeTouchListener;
import me.xunhou.v2ex.ui.fragment.ForumListFragment;

/**
 * Created by ihgoo on 2015/5/18.
 */
public class MainActivity extends AppCompatActivity {

    private AccountHeader headerResult;
    private int mQuit = 0;
    private OnSwipeTouchListener mSwipeListener;
    public Drawer drawerResult;

    private final int DRAWER_POSITION_PROGRAMMER = 0;
    private final int DRAWER_POSITION_CREATE = 1;
    private final int DRAWER_POSITION_SHARE = 2;
    private final int DRAWER_POSITION_APPLE = 3;
    private final int DRAWER_POSITION_JOBS = 4;
    private final int DRAWER_POSITION_IDEAS = 5;
    private final int DRAWER_POSITION_QNA = 6;
    private final int DRAWER_POSITION_SETTING = 7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_main_page);
        ButterKnife.inject(this);

        setupDrawer(savedInstanceState);

        mSwipeListener = new OnSwipeTouchListener(this) {
            public void onSwipeRight() {
                popFragment(false);
            }
        };

        findViewById(R.id.main_frame_container).setOnTouchListener(mSwipeListener);
        getFragmentManager().addOnBackStackChangedListener(new BackStackChangedListener());
        initFristFragment();
    }

    private void initFristFragment() {
        Fragment fragment = new ForumListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IntentConstant.NODE, "programmer");
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                .commit();
    }


    private void setupDrawer(Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);


        final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon("https://avatars3.githubusercontent.com/u/1476232?v=3&s=460");


        headerResult = new AccountHeaderBuilder()

                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withCompactStyle(true)
                .withSelectionListEnabled(false)
                .addProfiles(profile)
                .withSavedInstance(savedInstanceState)
                .build();


        ArrayList<IDrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_programmer).withIcon(GoogleMaterial.Icon.gmd_create));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_create).withIcon(GoogleMaterial.Icon.gmd_grade));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_share).withIcon(GoogleMaterial.Icon.gmd_forum));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_apple).withIcon(GoogleMaterial.Icon.gmd_favorite));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_jobs).withIcon(GoogleMaterial.Icon.gmd_mail).withBadgeTextColor(Color.RED));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_ideas).withIcon(GoogleMaterial.Icon.gmd_whatshot).withBadgeTextColor(Color.RED));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_qna)
                .withIcon(GoogleMaterial.Icon.gmd_adb));


        drawerResult = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withSliderBackgroundColorRes(R.color.white_t)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(true)
                .withDrawerItems(drawerItems)
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
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

        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 10
            drawerResult.setSelectionByIdentifier(10, false);
        }

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
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mQuit = 1;
                }
            },5000);
            if (mQuit == 1) {
                Toast.makeText(this, R.string.exit_app, Toast.LENGTH_LONG).show();
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

            Log.e("", "position:" + position);
            Fragment fragment = new ForumListFragment();
            Bundle bundle = new Bundle();
            switch (position) {
                case DRAWER_POSITION_PROGRAMMER:
                    bundle.putString(IntentConstant.NODE, "programmer");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case DRAWER_POSITION_CREATE:
                    bundle.putString(IntentConstant.NODE, "create");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case DRAWER_POSITION_SHARE:
                    bundle.putString(IntentConstant.NODE, "share");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case DRAWER_POSITION_APPLE:
                    bundle.putString(IntentConstant.NODE, "apple");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case DRAWER_POSITION_JOBS:
                    bundle.putString(IntentConstant.NODE, "jobs");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case DRAWER_POSITION_IDEAS:
                    bundle.putString(IntentConstant.NODE, "ideas");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case DRAWER_POSITION_QNA:
                    bundle.putString(IntentConstant.NODE, "qna");
                    fragment.setArguments(bundle);
                    getFragmentManager().beginTransaction()
                            .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                            .commit();
                    break;
                case DRAWER_POSITION_SETTING:
                    Intent intentSetting = new Intent();
                    // TODO 设置界面需要重写
//                    intentSetting.setClass(MainActivity.this, SettingActivity.class);
//                    startActivity(intentSetting);

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
