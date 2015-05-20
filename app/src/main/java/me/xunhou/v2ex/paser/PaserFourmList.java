package me.xunhou.v2ex.paser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import me.xunhou.v2ex.model.ForumItemBean;
import me.xunhou.v2ex.model.Member;
import me.xunhou.v2ex.ui.Misc;

/**
 * Created by ihgoo on 2015/5/20.
 */
public class PaserFourmList {



    public static ArrayList<ForumItemBean> paser2ForumItem(String string){
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

                int indexOf = time.indexOf("前");
                if (indexOf!=-1){
                    time = time.substring(0, indexOf);
                }

                ForumItemBean forumItemBean = new ForumItemBean();
                Member member = new Member();
                member.setAvatarMini(avatar);
                member.setUsername(username);
                forumItemBean.setMember(member);
                forumItemBean.setLastTime(time);
                forumItemBean.setReplies(Misc.parseInt(countLivid,0));
                forumItemBean.setTitle(element.select(".item_title").first().select("[href]").html());
                list.add(forumItemBean);
        }
        return list;

    }

}
