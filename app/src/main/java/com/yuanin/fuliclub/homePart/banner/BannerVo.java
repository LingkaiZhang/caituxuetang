package com.yuanin.fuliclub.homePart.banner;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * @author：tqzhang  on 18/5/8 15:40
 */
public class BannerVo extends SimpleBannerInfo {


    /**
     * id : 8
     * title : test
     * picture : https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/6d7613468c6244099ef37c0a4cfe746f.jpg
     * courseId : 30
     * link :
     * number : 2
     * type : 1
     * isBuy : 1
     * status : 0
     * updateTime : 1571741363000
     * createTime : 1571190943000
     */

    private int id;
    private String title;
    private String picture;
    private int courseId;
    private String link;
    private int number;
    private int type;
    private int isBuy;
    private int status;
    private long updateTime;
    private long createTime;

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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }


    @Override
    public String getXBannerUrl() {
        return getPicture();
    }

    @Override
    public String getXBannerTitle() {
        return getTitle();
    }
}
