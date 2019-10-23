package com.yuanin.fuliclub.coursePart.bean;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期二 2019/10/22
 * @version :
 * @name :
 */
public class ClassInfoVo {


    /**
     * teacher : 您的班级还没班主任或您没加入班级！
     * in : 2
     */

    private Teacher teacher;
    private int in;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public class Teacher {
        private int id;
        private String wx_url;
        private String wx_number;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getWx_url() {
            return wx_url;
        }

        public void setWx_url(String wx_url) {
            this.wx_url = wx_url;
        }

        public String getWx_number() {
            return wx_number;
        }

        public void setWx_number(String wx_number) {
            this.wx_number = wx_number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
