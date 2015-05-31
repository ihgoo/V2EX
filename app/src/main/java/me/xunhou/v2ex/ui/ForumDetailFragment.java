package me.xunhou.v2ex.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.xunhou.v2ex.R;
import me.xunhou.v2ex.core.ForumDetail;
import me.xunhou.v2ex.model.ForumItemBean;
import me.xunhou.v2ex.model.ReplyBean;
import me.xunhou.v2ex.model.TopicBean;
import me.xunhou.v2ex.persistence.IntentConstant;
import me.xunhou.v2ex.utils.BusProvider;
import me.xunhou.v2ex.utils.ToastUtil;

/**
 * Created by ihgoo on 2015/5/21.
 */
public class ForumDetailFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    TextView title;
    ForumDetail forumDetail;
    List<ReplyBean> mList = new ArrayList<ReplyBean>();
    ForumDetailAdapter forumDetailAdapter;
    ForumItemBean forumItemBean;

    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.action_fab_quick_reply)
    FloatingActionButton actionFabQuickReply;
    @InjectView(R.id.action_fab_goto_page)
    FloatingActionButton actionFabGotoPage;
    @InjectView(R.id.fam_actions)
    FloatingActionMenu famActions;
    @InjectView(R.id.action_fab_refresh)
    FloatingActionButton actionFabRefresh;
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forumItemBean = (ForumItemBean) getArguments().getSerializable(IntentConstant.SerializableitemBean);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
    }


    @Override
    public void onStart() {
        super.onStart();
        BusProvider.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.unregister(this);
        forumDetail.cancel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum_detail, null);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BusProvider.register(this);
        forumDetail = new ForumDetail();

        title.setText(forumItemBean.getTitle());
        swipeContainer.setRefreshing(true);
        forumDetail.getForumDetail(forumItemBean.getId() + "");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_thread_list, menu);
        setActionBarTitle(forumItemBean.getTitle());


        syncActionBarState();
        super.onCreateOptionsMenu(menu, inflater);
    }



    @Subscribe
    public void getForumDetail(TopicBean topicBean) {
        mList.addAll(topicBean.getReplyBeanList());
        forumDetailAdapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }

    @Subscribe
    public void failure(String string) {
        ToastUtil.showLongTime(getActivity(), string);
    }


    private void initView() {
        View header = View.inflate(mContext, R.layout.header_forum_detail_content, null);
        title = (TextView) header.findViewById(R.id.tv_title);
        lv.addHeaderView(header);
        forumDetailAdapter = new ForumDetailAdapter(mContext, mList);
        lv.setAdapter(forumDetailAdapter);

        swipeContainer.setColorSchemeColors(R.color.primary);
        swipeContainer.setOnRefreshListener(this);
        swipeContainer.setRefreshing(false);

        actionFabRefresh.setImageDrawable(new IconicsDrawable(getActivity(), GoogleMaterial.Icon.gmd_refresh).color(Color.WHITE));
        actionFabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                famActions.close(true);
                refresh();
            }
        });

        actionFabQuickReply.setImageDrawable(new IconicsDrawable(getActivity(), GoogleMaterial.Icon.gmd_reply).color(Color.WHITE));
        actionFabQuickReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                famActions.close(false);
                famActions.setVisibility(View.INVISIBLE);
                actionFabQuickReply.setVisibility(View.VISIBLE);
                actionFabQuickReply.bringToFront();
//                (new Handler()).postDelayed(new Runnable() {
//                    public void run() {
//                        mReplyTextTv.requestFocus();
//                        mReplyTextTv.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
//                        mReplyTextTv.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
//                    }
//                }, 200);
            }
        });

        actionFabGotoPage.setImageDrawable(new IconicsDrawable(getActivity(), GoogleMaterial.Icon.gmd_swap_horiz).color(Color.WHITE));
        actionFabGotoPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                famActions.close(true);
//                showGotoPageDialog();
            }
        });

    }

    private void refresh() {
        forumDetail.getForumDetail(forumItemBean.getId() + "");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        BusProvider.unregister(this);
        forumDetail.cancel();
    }

    @Override
    public void onRefresh() {
        swipeContainer.setRefreshing(true);
        refresh();
    }
}
