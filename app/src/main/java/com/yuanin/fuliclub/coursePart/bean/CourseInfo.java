package com.yuanin.fuliclub.coursePart.bean;

import com.google.gson.annotations.SerializedName;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期二 2019/10/22
 * @version :
 * @name :
 */
public class CourseInfo {


    /**
     * courseTitle : 实操跟练，通俗易懂
     * smallPicture : https://fuliketang-prod-pub.oss-cn-shanghai.aliyuncs.com/955aee2af21443f9a365358ed815f8aa.png
     * courseName : 小白训练营
     * endDate : 1571932800000
     * periodsId : 36
     * startDate : 1571673600000
     * background; : https://fuliketang-prod-pub.oss-cn-shanghai.aliyuncs.com/c1d2091090034e4dabe11f2e0884fa37.png
     */

    private String courseTitle;
    private String smallPicture;
    private String courseName;
    private long endDate;
    private int periodsId;
    private long startDate;
    private String background; // FIXME check this code

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public int getPeriodsId() {
        return periodsId;
    }

    public void setPeriodsId(int periodsId) {
        this.periodsId = periodsId;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public String get_$Background68() {
        return background;
    }

    public void set_$Background68(String _$Background68) {
        this.background = background;
    }
}
