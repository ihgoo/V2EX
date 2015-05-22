package me.xunhou.v2ex.model;

/**
 * Created by ihgoo on 2015/5/22.
 */
public class ReplyBean {
    private Member member;
    private String content;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
