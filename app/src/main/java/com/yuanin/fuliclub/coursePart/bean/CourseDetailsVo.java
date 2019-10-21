package com.yuanin.fuliclub.coursePart.bean;

import java.util.List;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期三 2019/10/16
 * @version :
 * @name :
 */
public class CourseDetailsVo {

    /**
     * id : 1
     * courseName : go
     * courseTitle : 测试课程测试
     * smallPicture :
     * courseApplyCrowd : 小白sdfcdsfcerdsf房貸市場都是非常
     * courseDetailUrls : ["https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/2eedeeec2c0e4b18bcdf2ea69b608efd.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/e59d18c61bf448f2984569a3e6c0d349.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/38c7c92eecf54b758f0c82144fdb9828.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/fc475f5e209041bd8a9b0a6af621611e.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/cb95ab8289a7461dada34e19cf96bc29.jphttps://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/d0a588b4bddc46de9109720351f86456.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/1fb0047948b745fca46c08e47c0350e9.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/8f4ecb70ee1a4c3b829f7c03f60b5f9a.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/d005b9f89f1c4832a37ccc61669356a6.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/5ccd86d82d714756a1c79c0a305187ff.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/489305f112cd4620a4170febc5f321b5.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/743bd290317b44fbb50c7a7924c13955.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/51a45871589b4d5eae470a7047c490e5.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/5f6c6ba6875f4dd2a62980ad61b3d406.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/27c37e465cae41aeabb10fa2607df1fb.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/a93bf427e0d8486ea47f1e452ecdc92e.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/9d67b8608cf9445bbef0364e732de966.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/5f3731afdbc94cfc9bbcb12a37aa0242.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/1aaf941f4f364fb397d5fa41631c6b6d.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/b609ccba39ee42bc99a3ca1fc4398a3a.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/4459f11565654aad810a8446b23cfc92.jpg"]
     * isBuy : 0
     * costPrice : 0.1
     */

    private int id;
    private String courseName;
    private String courseTitle;
    private String smallPicture;
    private String courseApplyCrowd;
    private int isBuy;
    private double costPrice;
    private List<String> courseDetailUrls;
    private String background;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

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

    public String getSmallPicture() {
        return smallPicture;
    }

    public void setSmallPicture(String smallPicture) {
        this.smallPicture = smallPicture;
    }

    public String getCourseApplyCrowd() {
        return courseApplyCrowd;
    }

    public void setCourseApplyCrowd(String courseApplyCrowd) {
        this.courseApplyCrowd = courseApplyCrowd;
    }

    public int getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public List<String> getCourseDetailUrls() {
        return courseDetailUrls;
    }

    public void setCourseDetailUrls(List<String> courseDetailUrls) {
        this.courseDetailUrls = courseDetailUrls;
    }
}
