package me.xunhou.v2ex.ui;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.xunhou.v2ex.R;
import me.xunhou.v2ex.model.TopicBean;

/**
 * Created by ihgoo on 2015/5/21.
 */
public class ForumDetailAdapter extends BaseAdapter {


    private Context mContext;

    private List<TopicBean> mList;

    public ForumDetailAdapter(Context context, List<TopicBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TopicBean topicBean = mList.get(position);
        view = View.inflate(mContext, R.layout.item_forum_detail_list, null);
        ViewHolder viewHolder = new ViewHolder(view);
//        viewHolder.tvName.setText(topicBean. + "");
//        viewHolder.tvTime.setText(forumItemBean.getLastTime() + "前");
        viewHolder.tvTitle.setText(topicBean.getContent());
//        ImageLoader.getInstance().displayImage(topicBean.getMember().getAvatarMini(), viewHolder.sdAvatar);
        return view;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_forum_detail_list.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.sd_avatar)
        SimpleDraweeView sdAvatar;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_time)
        TextView tvTime;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.cardview)
        CardView cardview;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}