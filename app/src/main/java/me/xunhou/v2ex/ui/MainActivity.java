package me.xunhou.v2ex.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_main_page);
        ButterKnife.inject(this);

        if (Build.VERSION.SDK_INT >= 21 ) {
            getWindow().setNavigationBarColor(Color.parseColor("00000000"));
        }

        setupDrawer();

        Log.e("", "........");
        getFragmentManager().addOnBackStackChangedListener(new BackStackChangedListener());
        Fragment fragment = new ForumListFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.main_frame_container, fragment, ForumListFragment.class.getName())
                .commit();
    }


    AccountHeader.Result headerResult;

    private void setupDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);



         //Create the AccountHeader
//        String username = VolleyHelper.getInstance().isLoggedIn() ? HiSettingsHelper.getInstance().getUsername() : "<未登录>";
//        String avatarUrl = VolleyHelper.getInstance().isLoggedIn() ? HiUtils.getAvatarUrlByUid(HiSettingsHelper.getInstance().getUid()) : "";
        headerResult = new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withCompactStyle(true)
                .withSelectionListEnabled(false)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withEmail("麦当劳")
                                .withIcon("http://cdn.v2ex.co/avatar/e19c/5147/42812_normal.png?m=1375151242")
                )
                .build();

        ArrayList<IDrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_search).withIcon(GoogleMaterial.Icon.gmd_search));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_mypost).withIcon(GoogleMaterial.Icon.gmd_grade));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_myreply).withIcon(GoogleMaterial.Icon.gmd_forum));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_favorites).withIcon(GoogleMaterial.Icon.gmd_favorite));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_sms).withIcon(GoogleMaterial.Icon.gmd_mail).withBadgeTextColor(Color.RED));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_notify).withIcon(GoogleMaterial.Icon.gmd_notifications).withBadgeTextColor(Color.RED));
        drawerItems.add(new PrimaryDrawerItem().withName(R.string.title_drawer_setting)
                .withIcon(GoogleMaterial.Icon.gmd_settings));

        ArrayList<IDrawerItem> stickyDrawerItems = new ArrayList<>();
//        for (int i = 0; i < HiUtils.FORUM_IDS.length; i++) {
//            if (HiUtils.isForumEnabled(HiUtils.FORUM_IDS[i]))
//                stickyDrawerItems.add(new PrimaryDrawerItem().withName(HiUtils.FORUMS[i])
//                        .withIdentifier(HiUtils.FORUM_IDS[i])
//                        .withIcon(HiUtils.FORUM_ICONS[i]));
//        }



        Drawer.Result drawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(true)
                .withDrawerItems(drawerItems)
                .withStickyDrawerItems(stickyDrawerItems)
//                .withOnDrawerItemClickListener(new DrawerItemClickListener())
                .build();

        //fix input layout problem when withTranslucentStatusBar enabled
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
                Fragment fg = fm.findFragmentById(R.id.main_frame_container);
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
