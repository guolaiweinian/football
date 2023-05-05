package com.liaoquanfeng.football.controller.response;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;


/**
 * 结果集对象
 *
 * @author thinkive
 * @version 1.0.0
 * @since 1.0
 */
public class ResponseModel<T> implements Serializable {

    private static final long serialVersionUID = -1263032784821448286L;

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public ResponseModel() {
        this(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMessage(), null);
    }

    public ResponseModel(T data) {
        this(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMessage(), data);
    }

    public ResponseModel(int code, String msg) {
        this(code, msg, null);
    }

    public ResponseModel(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean success() {
        return this.code == ResponseStatusEnum.SUCCESS.getCode();
    }

    public static <T> ResponseModel<T> ok() {
        return new ResponseModel<>();
    }

    public static <T> ResponseModel<T> ok(T data) {
        return new ResponseModel<>(data);
    }

    public static <T> ResponseModel<T> of(int code, String msg, T data) {
        return new ResponseModel<>(code, msg, data);
    }

    public static <T> ResponseModel<T> fail(int code, String msg) {
        return new ResponseModel<>(code, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
