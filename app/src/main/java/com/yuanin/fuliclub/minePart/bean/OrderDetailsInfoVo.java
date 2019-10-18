package com.yuanin.fuliclub.minePart.bean;

import java.io.Serializable;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期五 2019/10/18
 * @version :
 * @name :
 */
public class OrderDetailsInfoVo implements Serializable {

    /**
     * id : 5
     * orderNo : 20191018155924910axt10002
     * userId : 5
     * productType : 1
     * productId : 1
     * productName : go
     * beginTime : 1571385565000
     * price : 10
     * payWay : null
     * orderStatus : 1
     * validStatus : 0
     * enable : 1
     * version : 0
     * updateId : 0
     * updateTime : 1571387201000
     * createId : 0
     * createTime : 1571385565000
     * phoneModel : null
     * orderType : null
     * overTime : 1571387201000
     * periodsId : 1
     * userPhone : 15090601111
     * userName : vv还能还好吧
     * imageurl :
     * keywrod : null
     */

    private int id;
    private String orderNo;
    private int userId;
    private int productType;
    private int productId;
    private String productName;
    private long beginTime;
    private int price;
    private String payWay;
    private int orderStatus;
    private int validStatus;
    private int enable;
    private int version;
    private int updateId;
    private long updateTime;
    private int createId;
    private long createTime;
    private Object phoneModel;
    private String orderType;
    private long overTime;
    private int periodsId;
    private String userPhone;
    private String userName;
    private String imageurl;
    private Object keywrod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(int validStatus) {
        this.validStatus = validStatus;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Object getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(Object phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public long getOverTime() {
        return overTime;
    }

    public void setOverTime(long overTime) {
        this.overTime = overTime;
    }

    public int getPeriodsId() {
        return periodsId;
    }

    public void setPeriodsId(int periodsId) {
        this.periodsId = periodsId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Object getKeywrod() {
        return keywrod;
    }

    public void setKeywrod(Object keywrod) {
        this.keywrod = keywrod;
    }


}
