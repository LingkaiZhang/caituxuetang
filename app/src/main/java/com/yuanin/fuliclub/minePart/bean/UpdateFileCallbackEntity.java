package com.yuanin.fuliclub.minePart.bean;

import java.io.Serializable;

public class UpdateFileCallbackEntity implements Serializable {

    /**
     * saveName : 85b6c02ee4fe4be88861a60f545db7d1.jpg
     * disName : 0bd162d9f2d3572c25e340088013632763d0c3e5.jpg
     */

    private String saveName;
    private String disName;

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }
}
