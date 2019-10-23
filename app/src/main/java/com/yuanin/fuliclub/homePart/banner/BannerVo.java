package com.yuanin.fuliclub.homePart.banner;

/**
 * @author：tqzhang  on 18/5/8 15:40
 */
public class BannerVo {


    /**
     * id : 10
     * title : 小白训练营
     * picture : https://fuliketang-prod-pub.oss-cn-shanghai.aliyuncs.com/b1cd506634be411aab8fba7c075b8166.png
     * link : https://www.baidu.com
     * number : 1
     */

    private int id;
    private String title;
    private String picture;
    private String link;
    private int number;

    public BannerVo() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
