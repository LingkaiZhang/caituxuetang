package com.yuanin.fuliclub.coursePart.bean;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期四 2019/10/17
 * @version :
 * @name :
 */
public class WeChatOrderVo {

    /**
     * timeStamp : 1543285767
     * packageValue : Sign=WXPay
     * appid : wxef677fe2297939e3
     * sign : 15f5f3f301aa17730a798da19a6d2e29
     * partnerid : 1519346141
     * prepayid : wx2710291734409482e625ffbc2235595102
     * nonceStr : 6EQx09ePILmlxu03
     */

    private String timeStamp;
    private String packageValue;
    private String appid;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String nonceStr;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
}
