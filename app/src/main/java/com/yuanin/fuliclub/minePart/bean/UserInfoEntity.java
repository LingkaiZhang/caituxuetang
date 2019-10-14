package com.yuanin.fuliclub.minePart.bean;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期一 2019/10/14
 * @version :
 * @name :
 */
public class UserInfoEntity {

    /**
     * uid : 4
     * userStatus : 1
     * nickName : 150****2313
     * mobile : 15090602313
     * profilePictureLink :
     */

    private String uid;
    private String userStatus;
    private String nickName;
    private String mobile;
    private String profilePictureLink;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

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
}
