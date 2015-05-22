package me.xunhou.v2ex.model;

import java.util.List;

/**
 * Created by ihgoo on 2015/5/21.
 */
public class TopicBean {

    private List<ReplyBean> replyBeanList;

    public List<ReplyBean> getReplyBeanList() {
        return replyBeanList;
    }

    public void setReplyBeanList(List<ReplyBean> replyBeanList) {
        this.replyBeanList = replyBeanList;
    }
}
