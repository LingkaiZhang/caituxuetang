package com.yuanin.fuliclub.minePart.bean;

import java.io.Serializable;

public class PersonalInfoEntity implements Serializable {

    /**
     * nickName : 悟空
     * mobile : 13476594866
     * profilePictureLink : aaaaaaaaa
     * relevance : 0
     */

    private String nickName;
    private String mobile;
    private String profilePictureLink;
    private int relevance;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfilePictureLink() {
        return profilePictureLink;
    }

    public void setProfilePictureLink(String profilePictureLink) {
        this.profilePictureLink = profilePictureLink;
    }

    public int getRelevance() {
        return relevance;
    }

    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }
}
