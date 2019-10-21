package com.yuanin.fuliclub.coursePart.bean;

import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;
import java.util.List;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期一 2019/10/21
 * @version :
 * @name :
 */
@Table("KnobbleDetails")
public class KnobbleDetailsInfoVo implements Serializable {

    /**
     * id : 237
     * parentId : 30
     * classHourName : 港股打新第一步
     * serialNumber : 1
     * tryOut : 0
     * mp3Url :
     * mp3Time : 310
     * isWork : 0
     * childDetailList : ["https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/e1b745143cc0443c8ae15d68d06858e2.jpeg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/679fcb807e1b484aae315f5186ed1b0b.jpg","https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/2ed2c22f75ac4b4dbc906dc20482c957.jpg"]
     * courseName : 港股打新五部曲
     * thumbnail : https://fuliketang-test-pub2.oss-cn-shanghai.aliyuncs.com/a4888450e3b84e048582fc9c6a3405ac.jpg
     */

    /**
     * 主键，使用当前Id的值
     */
    @PrimaryKey(AssignType.BY_MYSELF)
    private String id;

    /**
     * 时长，在线音乐播放后，才可以取值
     */
    private long duration;

    /**
     * 用户Id，保存到数据库采用，用来实现多用户
     */
    private String userId;

    /**
     * 是否在播放列表，true：在
     */
    private boolean playList;

    private int parentId;
    private String classHourName;
    private int serialNumber;
    private int tryOut;
    private String mp3Url;
    private int mp3Time;
    private int isWork;
    private String courseName;
    private String thumbnail;

    @Ignore
    private List<String> childDetailList;


    public boolean isPlayList() {
        return playList;
    }

    public void setPlayList(boolean playList) {
        this.playList = playList;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getIsWork() {
        return isWork;
    }

    public void setIsWork(int isWork) {
        this.isWork = isWork;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getChildDetailList() {
        return childDetailList;
    }

    public void setChildDetailList(List<String> childDetailList) {
        this.childDetailList = childDetailList;
    }
}
