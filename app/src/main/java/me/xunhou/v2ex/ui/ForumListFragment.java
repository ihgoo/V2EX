package me.xunhou.v2ex.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperCardToast;
import com.github.johnpersano.supertoasts.SuperToast;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.xunhou.v2ex.R;
import me.xunhou.v2ex.core.FourmList;
import me.xunhou.v2ex.model.ForumItemBean;
import me.xunhou.v2ex.persistence.IntentConstant;
import me.xunhou.v2ex.utils.BusProvider;
import me.xunhou.v2ex.utils.ToastUtil;

/**
 * Created by ihgoo on 2015/5/19.
 */
public class ForumListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.goBack)
    ImageView goBack;
    @InjectView(R.id.menuButton)
    ImageView menuButton;
    @InjectView(R.id.mainTitile)
    TextView mainTitile;
    @InjectView(R.id.right_btn)
    ImageView rightBtn;
    @InjectView(R.id.right_tv)
    TextView rightTv;
    @InjectView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;


    private FourmList mFourmList;
    private ForumListAdapter forumListAdapter;
    private ArrayList<ForumItemBean> mList = new ArrayList<>();

    private int page = 0;

    private boolean isLoading = false;

    private boolean isRefresh = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mFourmList = new FourmList();
        if (mList.size()==0){
            mFourmList.getTopicsList(page);
            swipeContainer.setRefreshing(true);
        }
    }

    private void initView() {
        forumListAdapter = new ForumListAdapter(getActivity(), mList);
        lv.setAdapter(forumListAdapter);
        mainTitile.setText("V2EX");
        goBack.setVisibility(View.GONE);
        swipeContainer.setOnRefreshListener(this);
        swipeContainer.setColorSchemeColors(R.color.blue);
        lv.setOnScrollListener(this);
        lv.setOnItemClickListener(this);
    }

    @Subscribe
    public void getTopicsList(ArrayList<ForumItemBean> list) {
        getFourmList(list, isRefresh);
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
        superCardToast.setText("已更新" + list.size() + "条数据");
        superCardToast.setTextSize(14);
        superCardToast.setBackground(R.color.blue_transparent);
        superCardToast.show();
        isLoading = false;
        swipeContainer.setRefreshing(false);
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
        isRefresh = true;
        swipeContainer.setRefreshing(true);
        mFourmList.getTopicsList(page);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE:
                if (view.getLastVisiblePosition() == (view.getCount() - 1)) {

                    if (!isLoading) {
                        page++;
                        isLoading = true;
                        mFourmList.getTopicsList(page);
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
                .replace(R.id.content_frame, forumDetailFragment, ForumDetailFragment.class.getName())
                .addToBackStack(ForumDetailFragment.class.getName())
                .commit();
    }


}
