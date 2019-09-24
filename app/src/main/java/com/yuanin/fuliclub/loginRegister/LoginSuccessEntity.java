package com.yuanin.fuliclub.loginRegister;

import java.io.Serializable;

public class LoginSuccessEntity implements Serializable {


    /**
     * accessToken : 7vY9rMHOX6jWjdutQp5QNHc4mhOwEIj6VqZMZJzWvQQ0Wr1nza7Gj6TdBvdKLZrY2gQwUM5lShennlLxTc3j9mkhao/UXeBLDaMED/U+pw6mtF8b6pI4AJzPJ8Q9L/ZN6f4dh+6Cw+h5XC4BZmhJWMUvVwvoWq51WVCYbhd0C5p5wG5BnX9eAj+6gnyewyFl
     * uid : 11
     * mobile : 15090602313
     */

    private String accessToken;
    private int uid;
    private int wxId;
    private String mobile;

    public int getWxId() {
        return wxId;
    }

    public void setWxId(int wxId) {
        this.wxId = wxId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
