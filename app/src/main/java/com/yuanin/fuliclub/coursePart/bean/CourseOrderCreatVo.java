package com.yuanin.fuliclub.coursePart.bean;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期四 2019/10/17
 * @version :
 * @name :
 */
public class CourseOrderCreatVo {

    /**
     * orderNo : 20191014192555948axt10001
     * userId : 3
     * productType : 2
     * productId : null
     * productName : 测试课程2
     * productInfo : 测试课程测试2
     * price : 0.10
     * key : ad5e80c37d57a5fd1aef3b1552a92470
     * imageUrl : shanghai.aliyuncs.com/b82606aea4a04f818278f21c403d3c58.jpg
     * orderTerm : null
     * orderStatus : null
     * periodsId : 2
     * phoneModel : null
     * userPhone : null
     * userName : 刘强
     */

    private String orderNo;
    private int userId;
    private int productType;
    private String productId;
    private String productName;
    private String productInfo;
    private String price;
    private String key;
    private String imageUrl;
    private Object orderTerm;
    private Object orderStatus;
    private int periodsId;
    private Object phoneModel;
    private Object userPhone;
    private String userName;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Object getOrderTerm() {
        return orderTerm;
    }

    public void setOrderTerm(Object orderTerm) {
        this.orderTerm = orderTerm;
    }

    public Object getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Object orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPeriodsId() {
        return periodsId;
    }

    public void setPeriodsId(int periodsId) {
        this.periodsId = periodsId;
    }

    public Object getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(Object phoneModel) {
        this.phoneModel = phoneModel;
    }

    public Object getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Object userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
