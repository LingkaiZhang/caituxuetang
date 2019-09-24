package com.yuanin.fuliclub.base;

import java.io.Serializable;

public class ReturnResult<T> implements Serializable {

    //网络请求反回状态码 200 为成功， 其余为失败
    private int code;

    //成功/失败原因
    private String message;

    //接口执行成功时，返回的数据内容
    public T data;

    //请求成功失败判断
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    //获取到对象后，判断RecordDetail是否为null，为null则没有数据
    public boolean isNotNull() {
        return data != null && data.toString().length() > 0;
    }

    @Override
    public String toString() {
        return "ReturnResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", success=" + success +
                '}';
    }
}
