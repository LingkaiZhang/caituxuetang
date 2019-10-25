package com.yuanin.fuliclub.learnPart;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期日 2019/9/29
 * @version :
 * @name :
 */
public class LastLearnVo {

    /**
     * id : 5
     * userId : 1
     * courseId : 1
     * courseName : 测试课程名称
     * courseTitle : 测试课程测试
     * smallPicture : https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/5de2ab9a46e2457aa27c703ad0db7f7c.jpg
     * createTime : 1571032611000
     * updateTime : 1571037455000
     * isBuy : 1
     */

    private int id;
    private int userId;
    private int courseId;
    private String courseName;
    private String courseTitle;
    private String smallPicture;
    private long createTime;
    private long updateTime;
    private int isBuy;

    private boolean unReadMessage;

    public boolean isUnReadMessage() {
        return unReadMessage;
    }

    public void setUnReadMessage(boolean unReadMessage) {
        this.unReadMessage = unReadMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getSmallPicture() {
        return smallPicture;
    }

    public void setSmallPicture(String smallPicture) {
        this.smallPicture = smallPicture;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
    }
}
