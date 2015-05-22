package me.xunhou.v2ex.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ihgoo on 2015/5/20.
 */
public class StringUtil {

    /**
     * @param in
     * @return
     * @throws IOException
     */
    public static String  inputStream2String(InputStream in) {
        String string = "";
        try {
            byte[] buf = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (int i; (i = in.read(buf)) != -1; ) {
                baos.write(buf, 0, i);
            }
            string = baos.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static String delHTMLTag(String htmlStr){
        String regEx_script="<script[^>]*?>[//s//S]*?<///script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[//s//S]*?<///style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签
        return htmlStr.trim(); //返回文本字符串
    }
}
