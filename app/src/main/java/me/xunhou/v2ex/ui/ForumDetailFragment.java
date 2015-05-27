package me.xunhou.v2ex.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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
public class ForumDetailFragment extends BaseFragment {


    TextView title;
    ForumDetail forumDetail;
    List<ReplyBean> mList = new ArrayList<ReplyBean>();
    ForumDetailAdapter forumDetailAdapter;


    @InjectView(R.id.lv)
    ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        ForumItemBean forumItemBean = (ForumItemBean) getArguments().getSerializable(IntentConstant.SerializableitemBean);
        title.setText(forumItemBean.getTitle());
        forumDetail.getForumDetail(forumItemBean.getId() + "");
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }


    @Subscribe
    public void getForumDetail(TopicBean topicBean) {
        mList.addAll(topicBean.getReplyBeanList());
        forumDetailAdapter.notifyDataSetChanged();
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
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        BusProvider.unregister(this);
        forumDetail.cancel();
    }
}
