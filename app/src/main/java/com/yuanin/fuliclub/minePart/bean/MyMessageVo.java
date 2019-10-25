package com.yuanin.fuliclub.minePart.bean;

/**
 * description ： TODO:类的作用
 * author : lingkai
 * date : 2019/9/26 16:23
 */
public class MyMessageVo {

    /**
     * content : 您购买的《保险实战课》还有3天就要开课啦！,     				赶紧加入班级群，跟着班班一起学习吧~
     * courseId : 35
     * createDate : 1571909407000
     * deleted : 0
     * id : 1
     * status : 0
     * title : 《保险实战课》还有3天就要开课啦！
     * userId : 1780012
     */

    private String content;
    private int courseId;
    private long createDate;
    private int deleted;
    private int id;
    private int status;
    private String title;
    private int userId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
