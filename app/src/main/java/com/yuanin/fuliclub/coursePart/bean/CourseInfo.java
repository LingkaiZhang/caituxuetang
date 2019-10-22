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
     * smallPicture : https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/e4b1f45399d24b5b8aa1c1414c99f0f4.png
     * courseName : 小白训练营
     * background; : https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/965f4cf2691a4541b7a6b8ff33671723.png
     */

    private String courseTitle;
    private String smallPicture;
    private String courseName;
    @SerializedName("background;")
    private String _$Background262; // FIXME check this code

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

    public String get_$Background262() {
        return _$Background262;
    }

    public void set_$Background262(String _$Background262) {
        this._$Background262 = _$Background262;
    }
}
