package me.xunhou.v2ex.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;

import com.github.clans.fab.FloatingActionButton;
import com.github.johnpersano.supertoasts.SuperCardToast;
import com.github.johnpersano.supertoasts.SuperToast;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.xunhou.v2ex.R;
import me.xunhou.v2ex.core.FourmList;
import me.xunhou.v2ex.model.ForumItemBean;
import me.xunhou.v2ex.persistence.IntentConstant;
import me.xunhou.v2ex.ui.BaseFragment;
import me.xunhou.v2ex.ui.LoginDialog;
import me.xunhou.v2ex.ui.adapter.ForumListAdapter;
import me.xunhou.v2ex.utils.BusProvider;
import me.xunhou.v2ex.utils.ToastUtil;

/**
 * Created by ihgoo on 2015/5/19.
 */
public class ForumListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {


    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @InjectView(R.id.card_container)
    LinearLayout cardContainer;
    @InjectView(R.id.action_fab_new_thread)
    FloatingActionButton actionFabNewThread;

    private FourmList mFourmList;
    private ForumListAdapter forumListAdapter;
    private ArrayList<ForumItemBean> mList = new ArrayList<>();

    private int page = 1;

    private boolean isLoading = false;

    private boolean isRefresh = false;

    private String mNode;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getRemoteData();
    }

    private void getRemoteData() {
        mNode = getArguments().getString(IntentConstant.NODE);
        if (mNode == null) {
            new IllegalArgumentException("Node is null!!!plase check it.");
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum_list, null);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BusProvider.register(this);
        isLoading = false;
        mFourmList = new FourmList();
        if (mList.size()==0){
            swipeContainer.post(new Runnable() {
                @Override
                public void run() {
                    swipeContainer.setRefreshing(true);
                }
            });
            mFourmList.getTopicsList(page, mNode);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isLoading = false;
    }

    private void initView() {
        forumListAdapter = new ForumListAdapter(getActivity(), mList);
        lv.setAdapter(forumListAdapter);
        swipeContainer.setOnRefreshListener(this);
        swipeContainer.setColorSchemeResources(R.color.colorPrimary);
        lv.setOnScrollListener(this);
        lv.setOnItemClickListener(this);

        actionFabNewThread.setImageDrawable(new IconicsDrawable(getActivity(), GoogleMaterial.Icon.gmd_create).color(Color.WHITE));
        actionFabNewThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newThread();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        setActionBarTitle(mNode.toUpperCase());
        syncActionBarState();
        int forumIdx = 1;
        setDrawerSelection(forumIdx);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void newThread() {
        LoginDialog loginDialog = new LoginDialog(getActivity(),R.style.MyDialogStyle);
        loginDialog.setHander(new Handler());
        loginDialog.show();
    }

    @Subscribe
    public void getTopicsList(ArrayList<ForumItemBean> list) {
        getFourmList(list, isRefresh);
        swipeContainer.setRefreshing(false);
    }

    @Subscribe
    public void failure(String string) {
        ToastUtil.showLongTime(getActivity(), string);
    }


    private void getFourmList(ArrayList<ForumItemBean> list, boolean isReload) {
        if (isReload) {
            mList.clear();
            isRefresh = false;
        }

        mList.addAll(list);
        forumListAdapter.notifyDataSetChanged();
        SuperCardToast superCardToast = new SuperCardToast(getActivity(), SuperToast.Type.STANDARD);
        if (page == 1) {
            superCardToast.setText("正在浏览" + mNode + "节点...");
        } else {
            superCardToast.setText("正在浏览第" + (page) + "页...");
        }


        superCardToast.setTextSize(14);
        superCardToast.setBackground(R.color.colorPrimary);
        superCardToast.show();
        isLoading = false;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFourmList.cancel();
        ButterKnife.reset(this);
        BusProvider.unregister(this);
    }

    @Override
    public void onRefresh() {
        page = 1;
        isRefresh = true;
        mFourmList.getTopicsList(page, mNode);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE:
                if (view.getLastVisiblePosition() == (view.getCount() - 1)) {

                    if (!isLoading) {
                        page++;
                        isLoading = true;
                        swipeContainer.setRefreshing(true);
                        mFourmList.getTopicsList(page, mNode);
                    }

                }
                break;
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ForumItemBean itemBean = (ForumItemBean) forumListAdapter.getItem(position);
        ForumDetailFragment forumDetailFragment = new ForumDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(IntentConstant.SerializableitemBean, itemBean);
        forumDetailFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.main_frame_container, forumDetailFragment, ForumDetailFragment.class.getName())
                .addToBackStack(ForumDetailFragment.class.getName())
                .commit();
    }


}
