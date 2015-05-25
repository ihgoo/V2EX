package me.xunhou.v2ex.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import me.xunhou.v2ex.model.ForumItemBean;
import me.xunhou.v2ex.model.Member;
import me.xunhou.v2ex.model.ReplyBean;
import me.xunhou.v2ex.model.TopicBean;

/**
 * Created by ihgoo on 2015/5/25.
 */
public class V2EXPaser {

    public static String paserOnce(String string) {
        Document document = Jsoup.parse(string);
        String once = document.select("[name=once]").first().attr("value");
        return once;
    }

    public static ArrayList<ForumItemBean> paser2ForumItem(String string) {
        Document document = Jsoup.parse(string);
        Elements elements = document.select(".cell").select(".item");

        ArrayList<ForumItemBean> list = new ArrayList<>();
        for (Element element : elements) {
            // avatar
            // node
            // title
            // small fade (time)
            // small fade author
            // count_livid
            String avatar = element.select(".avatar").first().attr("src");
            String node = element.select(".node").first().html();
            String username = element.select(".small > strong").first().text();
            String countLivid = element.getElementsByClass("count_livid").text();
            String time = element.select(".small").select(".fade").get(1).text();


            String href = element.getElementsByClass("item_title").html();
            if (href.length()!=0){
                href = href.substring(12, href.indexOf("#"));
            }


            int indexOf = time.indexOf("前");
            if (indexOf != -1) {
                time = time.substring(0, indexOf);
            }

            ForumItemBean forumItemBean = new ForumItemBean();
            Member member = new Member();
            member.setAvatarMini(avatar);
            member.setUsername(username);
            forumItemBean.setId(Misc.parseInt(href, 0));
            forumItemBean.setMember(member);
            forumItemBean.setLastTime(time);
            forumItemBean.setReplies(Misc.parseInt(countLivid, 0));
            forumItemBean.setTitle(element.select(".item_title").first().select("[href]").html());
            list.add(forumItemBean);
        }
        return list;

    }


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
