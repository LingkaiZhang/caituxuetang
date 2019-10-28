package com.yuanin.fuliclub.learnPart;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期五 2019/10/25
 * @version :
 * @name :
 */
public class CourseKnobbleNoteInfoVo {


    /**
     * id : 23
     * childId : 1
     * name : 课程学习须知
     * analyzes : note
     * updateTime :
     */

    private int id;
    private int childId;
    private String name;
    private String analyzes;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnalyzes() {
        return analyzes;
    }

    public void setAnalyzes(String analyzes) {
        this.analyzes = analyzes;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
