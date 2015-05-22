package me.xunhou.v2ex.paser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import me.xunhou.v2ex.model.TopicBean;

/**
 * Created by ihgoo on 2015/5/21.
 */
public class PaserFourmDetail {

    public static TopicBean paserFourmDetail(String string){
        Document document = Jsoup.parse(string);
        String title = document.select("title").first().html();
        String topic_content = document.select(".topic_content").first().html();

        TopicBean topicBean = new TopicBean();
        topicBean.setTitle(title);
        topicBean.setContent(topic_content);
        return topicBean;

    }
}
