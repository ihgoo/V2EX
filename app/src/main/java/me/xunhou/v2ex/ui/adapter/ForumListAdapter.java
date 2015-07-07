package me.xunhou.v2ex.ui.adapter;

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
import me.xunhou.v2ex.model.ForumItemBean;
import me.xunhou.v2ex.utils.ImageLoader;

/**
 * Created by ihgoo on 2015/5/19.
 */
public class ForumListAdapter extends BaseAdapter {

    private Context mContext;

    private List<ForumItemBean> mList;

    public ForumListAdapter(Context context, List<ForumItemBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ForumItemBean forumItemBean = mList.get(position);
        view = View.inflate(mContext, R.layout.item_forum_list, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tvName.setText(forumItemBean.getMember().getUsername() + "");
        viewHolder.tvReplay.setText(forumItemBean.getReplies() + "");
        viewHolder.tvTitle.setText(forumItemBean.getTitle());
        ImageLoader.getInstance().displayImage(forumItemBean.getMember().getAvatarMini(), viewHolder.sdAvatar);
        return view;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_forum_list.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @InjectView(R.id.sd_avatar)
        SimpleDraweeView sdAvatar;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_replay)
        TextView tvReplay;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.cardview)
        CardView cardview;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
