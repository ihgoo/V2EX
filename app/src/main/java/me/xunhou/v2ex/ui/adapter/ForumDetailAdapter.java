package me.xunhou.v2ex.ui.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Spannable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.nightwhistler.htmlspanner.HtmlSpanner;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.xunhou.v2ex.R;
import me.xunhou.v2ex.model.ReplyBean;
import me.xunhou.v2ex.utils.ImageLoader;
import me.xunhou.v2ex.utils.StringUtil;

/**
 * Created by ihgoo on 2015/5/21.
 */
public class ForumDetailAdapter extends BaseAdapter {


    private Context mContext;

    private List<ReplyBean> mList;

    public ForumDetailAdapter(Context context, List<ReplyBean> list) {
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
        final ReplyBean replyBean = mList.get(position);

        view = View.inflate(mContext, R.layout.item_forum_detail_list, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        ImageLoader.getInstance().displayImage(replyBean.getMember().getAvatarMini(), viewHolder.sdAvatar);
        viewHolder.tvName.setText(replyBean.getMember().getUsername() + "");
        viewHolder.tvTime.setText((position + 1) + "#");
        viewHolder.tvTitle.setText(StringUtil.delHTMLTag(replyBean.getContent()));
        try {
            new AsyncTask<Void, String, Spannable>() {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                @Override
                protected Spannable doInBackground(Void... params) {
                    Spannable spannable = new HtmlSpanner().fromHtml(replyBean.getContent());
                    return spannable;
                }

                @Override
                protected void onPostExecute(Spannable spannable) {
                    viewHolder.tvTitle.setText(spannable);
                }
            }.execute();
        } catch (Exception e) {
            viewHolder.tvTitle.setText(StringUtil.delHTMLTag(replyBean.getContent()));
        }


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

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
