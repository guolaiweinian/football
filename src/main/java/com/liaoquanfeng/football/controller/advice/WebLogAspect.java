package com.liaoquanfeng.football.controller.advice;

import com.alibaba.fastjson.JSON;
import com.liaoquanfeng.football.controller.response.ResponseModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Web 请求日志切面
 *
 * @author thinkive
 * @version 1.0.0
 * @since 1.0
 */
@Aspect
@Component
@Order(-5)
public class WebLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    private static final String UNKNOWN = "unknown";

    private static final long MIN_WARN_COST_TIME = 1000L;

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (null == ip || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (null == ip || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) && (execution(* com.liaoquanfeng.football..*(..)))")
    public Object getInterceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return process(proceedingJoinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping) && (execution(* com.liaoquanfeng.footbal..*(..)))")
    public Object postInterceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return process(proceedingJoinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.PutMapping) && (execution(* com.liaoquanfeng.footbal..*(..)))")
    public Object putInterceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return process(proceedingJoinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.DeleteMapping) && (execution(* com.liaoquanfeng.footbal..*(..)))")
    public Object deleteInterceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return process(proceedingJoinPoint);
    }

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) && (execution(* com.liaoquanfeng.footbal..*(..)))")
    public Object requestInterceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return process(proceedingJoinPoint);
    }

    private Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String uri = request.getRequestURI();
            String method = request.getMethod();
            String remoteAddress = getIpAddress(request);
            String contentType = request.getContentType();
            String args = "";
            if (null != proceedingJoinPoint.getArgs() && 0 < proceedingJoinPoint.getArgs().length) {
                Object arg = proceedingJoinPoint.getArgs()[0];
                if (!(arg instanceof ServletRequest) && !(arg instanceof ServletResponse) && !(arg instanceof MultipartFile)) {
                    args = "with inputs: " + JSON.toJSONString(arg);
                }
                if (arg instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile)arg;
                    long size = file.getSize() / 1024; //kb
                    args = "upload-file-size: " + size + "kb";
                }
            }
            if (logger.isInfoEnabled()) {
                logger.info("{}\t{}\t{}\t{}\t{}", remoteAddress, method, uri, contentType, args);
            }
            result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();
            long cost = end - start;
            String output = "";
            if (result instanceof ResponseModel) {
                ResponseModel<?> model = (ResponseModel<?>) result;
                output = " code=" + model.getCode() + " \t  msg=\"" + model.getMsg() + "\"";
                if (null != model.getData()) {
                    output += " \t data=" + JSON.toJSONString(model.getData());
                }
            }
            if (MIN_WARN_COST_TIME <= cost) {
                if (logger.isWarnEnabled()) {
                    logger.warn("{}\t{}\t{}\t耗时:{}ms\t{}\t{}", remoteAddress, method, uri, cost, args, output);
                }
            } else {
                if (logger.isInfoEnabled()) {
                    logger.info("{}\t{}\t{}\t耗时:{}ms\t{}\t{}", remoteAddress, method, uri, cost, args, output);
                }
            }
        }
        return result;
    }
}
