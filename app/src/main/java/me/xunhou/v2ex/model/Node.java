package me.xunhou.v2ex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ihgoo on 2015/5/19.
 */
public class Node {
    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private String title;
    @SerializedName("title_alternative")
    @Expose
    private String titleAlternative;
    @Expose
    private String url;
    @Expose
    private Integer topics;
    @SerializedName("avatar_mini")
    @Expose
    private String avatarMini;
    @SerializedName("avatar_normal")
    @Expose
    private String avatarNormal;
    @SerializedName("avatar_large")
    @Expose
    private String avatarLarge;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The titleAlternative
     */
    public String getTitleAlternative() {
        return titleAlternative;
    }

    /**
     *
     * @param titleAlternative
     * The title_alternative
     */
    public void setTitleAlternative(String titleAlternative) {
        this.titleAlternative = titleAlternative;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The topics
     */
    public Integer getTopics() {
        return topics;
    }

    /**
     *
     * @param topics
     * The topics
     */
    public void setTopics(Integer topics) {
        this.topics = topics;
    }

    /**
     *
     * @return
     * The avatarMini
     */
    public String getAvatarMini() {
        return avatarMini;
    }

    /**
     *
     * @param avatarMini
     * The avatar_mini
     */
    public void setAvatarMini(String avatarMini) {
        this.avatarMini = avatarMini;
    }

    /**
     *
     * @return
     * The avatarNormal
     */
    public String getAvatarNormal() {
        return avatarNormal;
    }

    /**
     *
     * @param avatarNormal
     * The avatar_normal
     */
    public void setAvatarNormal(String avatarNormal) {
        this.avatarNormal = avatarNormal;
    }

    /**
     *
     * @return
     * The avatarLarge
     */
    public String getAvatarLarge() {
        return avatarLarge;
    }

    /**
     *
     * @param avatarLarge
     * The avatar_large
     */
    public void setAvatarLarge(String avatarLarge) {
        this.avatarLarge = avatarLarge;
    }
}
