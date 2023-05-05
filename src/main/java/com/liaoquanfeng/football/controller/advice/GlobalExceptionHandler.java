package com.liaoquanfeng.football.controller.advice;

import cn.hutool.core.util.StrUtil;
import com.liaoquanfeng.football.controller.response.ResponseModel;
import com.liaoquanfeng.football.controller.response.ResponseStatusEnum;
import com.liaoquanfeng.football.error.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.sql.SQLException;

/**
 * 描述：通用异常拦截
 *
 * @author thinkive
 * @version 1.0.0
 * @since 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 参数验证异常
     */
    private static ResponseModel<?> fromBindingResult(BindingResult bindingResult) {
        String message = "参数异常";
        if (bindingResult != null) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getDefaultMessage();
            }
        }
        return ResponseModel.fail(ResponseStatusEnum.PARAMETER_EXCEPTION.getCode(), message);
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseModel<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        if (logger.isWarnEnabled()) {
            logger.warn("参数校验异常：{}", e.getMessage());
        }
        return fromBindingResult(e.getBindingResult());
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseModel<?> beanPropertyBindingNotValidExceptionHandler(BindException e) {
        if (logger.isWarnEnabled()) {
            logger.warn("参数校验异常：{}", e.getMessage());
        }
        return fromBindingResult(e.getBindingResult());
    }

    /**
     * 数据库异常
     */
    @ExceptionHandler(SQLException.class)
    public ResponseModel<?> databaseException(SQLException e) {
        if (logger.isErrorEnabled()) {
            logger.error("数据库出现异常", e);
        }
        return ResponseModel.fail(ResponseStatusEnum.DATABASE_EXCEPTION.getCode(), "数据异常");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseModel<?> businessException(BusinessException e) {
        if (logger.isWarnEnabled()) {
            logger.warn("业务异常:[{}-{}]", e.getErrorNo(), e.getMessage());
        }
        return ResponseModel.fail(e.getErrorNo(), e.getMessage());
    }

//    /**
//     * 微服务调用异常
//     */
//    @ExceptionHandler(ClientException.class)
//    public ResponseModel<?> clientException(ClientException e) {
//        if (logger.isWarnEnabled()) {
//            logger.warn("远程调用异常:[{}-{}]", e.getErrorCode(), e.getMessage());
//        }
//        return ResponseModel.fail(ResponseStatusEnum.SYSTEM_EXCEPTION.getCode(), ResponseStatusEnum.SYSTEM_EXCEPTION.getMessage());
//    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class,
            MultipartException.class, MissingServletRequestPartException.class,
            MissingServletRequestParameterException.class})
    public ResponseModel<?> httpRequestExceptionHandler(Exception e) {
        if (logger.isWarnEnabled()) {
            logger.warn("接口调用异常:{}", e.getMessage());
        }
        String errorMessage = e.getMessage();
        if (StrUtil.contains(errorMessage, "Required request body is missing")) {
            return ResponseModel.fail(ResponseStatusEnum.SYSTEM_EXCEPTION.getCode(), "请求体格式错误");
        }
        if (StrUtil.contains(errorMessage, "Required request body is missing")) {
            return ResponseModel.fail(ResponseStatusEnum.SYSTEM_EXCEPTION.getCode(), "请求体格式错误");
        }
        if (StrUtil.contains(errorMessage, "JSON parse error")) {
            return ResponseModel.fail(ResponseStatusEnum.SYSTEM_EXCEPTION.getCode(), "请求参数格式错误");
        }
        if (StrUtil.contains(errorMessage, "Current request is not a multipart request")) {
            return ResponseModel.fail(ResponseStatusEnum.SYSTEM_EXCEPTION.getCode(), "请求类型错误");
        }
        if (StrUtil.contains(errorMessage, "Required request part")
                || StrUtil.contains(errorMessage, "Required String parameter")) {
            return ResponseModel.fail(ResponseStatusEnum.SYSTEM_EXCEPTION.getCode(), "缺失必要入参数据");
        }
        return ResponseModel.fail(ResponseStatusEnum.SYSTEM_EXCEPTION.getCode(), ResponseStatusEnum.SYSTEM_EXCEPTION.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Throwable.class)
    public ResponseModel<?> globalExceptionHandler(Throwable e) {
        if (logger.isErrorEnabled()) {
            logger.error("系统出现异常", e);
        }
        return ResponseModel.fail(ResponseStatusEnum.SYSTEM_EXCEPTION.getCode(), ResponseStatusEnum.SYSTEM_EXCEPTION.getMessage());
    }
}
