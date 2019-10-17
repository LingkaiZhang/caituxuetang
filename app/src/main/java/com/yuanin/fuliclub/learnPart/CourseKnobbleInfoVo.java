package com.yuanin.fuliclub.learnPart;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期四 2019/10/10
 * @version :
 * @name :
 */
public class CourseKnobbleInfoVo {

    /**
     * id : 1
     * parentId : 1
     * classHourName : 课程学习须知
     * serialNumber : 0
     * tryOut : 0
     * mp3Url : https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/cd02c22e9617443ab4f6fed1eff2b259.mp3
     * mp3Time : 209
     */

    private int id;
    private int parentId;
    private String classHourName;
    private int serialNumber;
    private int tryOut;
    private String mp3Url;
    private int mp3Time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getClassHourName() {
        return classHourName;
    }

    public void setClassHourName(String classHourName) {
        this.classHourName = classHourName;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getTryOut() {
        return tryOut;
    }

    public void setTryOut(int tryOut) {
        this.tryOut = tryOut;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public int getMp3Time() {
        return mp3Time;
    }

    public void setMp3Time(int mp3Time) {
        this.mp3Time = mp3Time;
    }
}
