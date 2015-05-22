package me.xunhou.v2ex.paser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import me.xunhou.v2ex.model.Member;
import me.xunhou.v2ex.model.ReplyBean;
import me.xunhou.v2ex.model.TopicBean;

/**
 * Created by ihgoo on 2015/5/21.
 */
public class PaserFourmDetail {

    public static TopicBean paserFourmDetail(String string) {
        Document document = Jsoup.parse(string);
        String title = document.select("title").first().html();
        String topic_content = document.getElementsByClass("topic_content").first().html();
        String contentAvatar = document.select(".avatar").first().attr("src");
        String author = document.select(".gray > [href]").first().html();
        Elements elements = document.select(".cell > table");
        TopicBean topicBean = new TopicBean();
        ArrayList<ReplyBean> replyBeans = new ArrayList<>();
        ReplyBean replyBean = new ReplyBean();
        Member member = new Member();
        member.setUsername(author);
        member.setAvatarMini(contentAvatar);
        replyBean.setMember(member);
        replyBean.setContent(topic_content);
        replyBeans.add(replyBean);

        for (Element element : elements) {
            String userName = element.select(".dark").first().attr("href");
            userName = userName.substring(10, userName.length());
            String reply_content = element.getElementsByClass("reply_content").first().html();
            String avatar = element.select(".avatar").first().attr("src");
            ReplyBean localReplyBean = new ReplyBean();
            Member localmember = new Member();
            localmember.setUsername(userName);
            localmember.setAvatarMini(avatar);
            localReplyBean.setMember(localmember);
            localReplyBean.setContent(reply_content);
            replyBeans.add(localReplyBean);
        }

        topicBean.setReplyBeanList(replyBeans);
        return topicBean;
    }


}
