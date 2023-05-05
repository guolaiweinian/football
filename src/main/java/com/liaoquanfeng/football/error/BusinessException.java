package com.liaoquanfeng.football.error;

import com.liaoquanfeng.football.controller.response.ResponseStatusEnum;

/**
 * @author liaoqf
 * @date $ $
 * @description
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 6335306571111870079L;

    private int errorNo;

    public BusinessException(int errorNo, String message) {
        super(message);
        this.errorNo = errorNo;
    }

    public BusinessException(int errorNo, String format, Object... args) {
        super(String.format(format, args));
        this.errorNo = errorNo;
    }

    public BusinessException(ResponseStatusEnum responseStatusEnum) {
        super(responseStatusEnum.getMessage());
        this.errorNo = responseStatusEnum.getCode();
    }

    public BusinessException(ResponseStatusEnum responseStatusEnum, String message) {
        super(message);
        this.errorNo = responseStatusEnum.getCode();
    }

    public int getErrorNo() {
        return errorNo;
    }

}