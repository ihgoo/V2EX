package me.xunhou.v2ex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ihgoo on 2015/5/19.
 */
public class Member  implements Serializable {
    @Expose
    private Integer id;
    @Expose
    private String username;
    @Expose
    private String tagline;
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
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The tagline
     */
    public String getTagline() {
        return tagline;
    }

    /**
     *
     * @param tagline
     * The tagline
     */
    public void setTagline(String tagline) {
        this.tagline = tagline;
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
