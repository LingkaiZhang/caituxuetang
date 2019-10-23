package com.yuanin.fuliclub.minePart.bean;

import java.util.List;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期二 2019/10/22
 * @version :
 * @name :
 */
public class MyMessageListVo {

    /**
     * number : 0
     * total : 0
     * data : []
     */

    private int number;
    private int total;
    private List<MyMessageVo> data;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MyMessageVo> getData() {
        return data;
    }

    public void setData(List<MyMessageVo> data) {
        this.data = data;
    }
}
