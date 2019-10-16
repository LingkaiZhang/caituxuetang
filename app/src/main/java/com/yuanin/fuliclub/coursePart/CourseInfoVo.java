package com.yuanin.fuliclub.coursePart;


import java.util.List;

public class CourseInfoVo {


    /**
     * id : 1
     * courseName : go
     * courseTitle : 测试课程测试
     * courseLabels : ["ewdsfc d"]
     * boughtNum : 6667
     * costPrice : 0.1
     * smallPicture :
     * isBuy : 0
     */

    private int id;
    private String courseName;
    private String courseTitle;
    private int boughtNum;
    private double costPrice;
    private String smallPicture;
    private int isBuy;
    private List<String> courseLabels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getBoughtNum() {
        return boughtNum;
    }

    public void setBoughtNum(int boughtNum) {
        this.boughtNum = boughtNum;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getSmallPicture() {
        return smallPicture;
    }

    public void setSmallPicture(String smallPicture) {
        this.smallPicture = smallPicture;
    }

    public int getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
    }

    public List<String> getCourseLabels() {
        return courseLabels;
    }

    public void setCourseLabels(List<String> courseLabels) {
        this.courseLabels = courseLabels;
    }
}
