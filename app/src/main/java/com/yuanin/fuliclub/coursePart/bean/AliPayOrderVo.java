package com.yuanin.fuliclub.coursePart.bean;

/**
 * <p>类说明</p>
 *
 * @author lingkai  星期四 2019/10/24
 * @version :
 * @name :
 */
public class AliPayOrderVo {

    /**
     * sin : alipay_sdk=alipay-sdk-java-4.8.10.ALL&app_id=2019092667828259&biz_content=%7B%22body%22%3A%22%E8%B4%A2%E5%85%94%E8%AF%BE%E5%A0%82-%22%2C%22out_trade_no%22%3A%2220191024173027885000caxt27%22%2C%22subject%22%3A%22%E6%B8%AF%E8%82%A1%E6%89%93%E6%96%B0%E8%AF%BE%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fopen.caituketang.com%2Fpay%2FaliPayCallback&sign=P%2FH1XLTH040RvsjWPjSG3nNuY61uRYVFxobKhJ7L6O22TtdZUmFtbiRRWGKno92hqfSlAw%2Btfsg1LbppxS0bS4RBDthOtFDyZVi05OnR2wtLe9n%2FRkgowNt8ekwRNqRvfPWIUEzROrvXRXhcIk0lfnYzjkY52WLmxVgsIU5LYhGTA316nU3qwNCw%2BeDiH53eipVX4w07WIe0VDAHBm5BpgVUkqUdG8NRoe8G%2F%2BDFF1FwJevc0YmYOzwf3m5XNUsI37f8IKXI2s7 2019-10-24 17:30:35.219 19192-19245/com.yuanin.fuliclub D/HttpLogger: Pc8Y8uX%2BPKW%2BuJdzOQx2X%2BifmThopeSjnzMY3CZsVXsdA1mmVKN0PFk99wOv0cEIB3JAKI6A08A%3D%3D&sign_type=RSA2&timestamp=2019-10-24+17%3A30%3A35&version=1.0
     */

    private String sin;

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }
}
