package com.liaoquanfeng.football.controller.response;

/**
 * 结果状态枚举
 *
 * @author thinkive
 * @version 1.0.0
 * @since 1.0
 */
public enum ResponseStatusEnum {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 系统异常
     */
    SYSTEM_EXCEPTION(10, "系统异常"),

    /**
     * 参数错误
     */
    PARAMETER_EXCEPTION(20, "参数错误"),

    /**
     * 数据库异常
     */
    DATABASE_EXCEPTION(30, "数据库异常"),

    //业务错误号定义规则：业务错误号由4位数字组成，前2位区分节点，后2位为业务错误号
    /**
     * 短信发送异常
     */
    SEND_SMS_EXCEPTION(1001, "短信发送异常"),

    /**
     * 图片验证码异常
     */
    CAPTCHA_EXCEPTION(1002, "图片验证码异常"),

    /**
     * 请输入图片验证码
     */
    CAPTCHA_EMPTY_EXCEPTION(1003, "请输入图片验证码"),

    /**
     * 登录认证异常
     */
    LOGIN_AUTH_EXCEPTION(1101, "登录认证异常"),

    /**
     * 营业部提交异常
     */
    SUBMIT_BRANCH_EXCEPTION(1201, "营业部提交异常"),

    /**
     * 身份证信息提交异常
     */
    ID_CARD_UPLOAD_EXCEPTION(1301, "身份证信息提交异常"),

    /**
     * 身份证上传识别异常
     */
    ID_CARD_OCR_EXCEPTION(1302, "身份证OCR识别失败"),

    /**
     * 身份证有效期异常
     */
    ID_CARD_VALID_DATE_EXCEPTION(1302, "身份证有效期异常"),

    /**
     * 身份证年龄异常
     */
    ID_CARD_AGE_EXCEPTION(1303, "身份证年龄异常"),

    /**
     * 身份证公安校验异常
     */
    ID_CARD_POLICE_CHECK_EXCEPTION(1304, "身份证公安校验异常"),

    /**
     * 身份证在黑名单异常
     */
    ID_CARD_BLACK_EXCEPTION(1305, "身份证在黑名单异常"),

    /**
     * 身份证已存在异常
     */
    ID_CARD_EXIST_EXCEPTION(1306, "身份证已存在异常"),

    /**
     * 柜台已存在该客户信息
     */
    CLIENT_EXIST_EXCEPTION(1307, "柜台已存在该客户信息"),

    /**
     * 身份证质检异常
     */
    ID_CARD_QC_EXCEPTION(1308, "身份证质检异常"),

    /**
     * 资料补充提交异常
     */
    ID_CONFIRM_EXCEPTION(1401, "资料补充提交异常"),

    /**
     * 邮箱格式错误
     */
    EMAIL_PATTERN_ERROR(1402, "邮件格式错误"),

    /**
     * 邮箱格式错误
     */
    INCOME_EMPTY_ERROR(1403, "年收入为空"),

    /**
     * 账户提交异常
     */
    STOCK_ACCOUNT_EXCEPTION(1501, "账户提交异常"),

    /**
     * 中登查询异常
     */
    QUERY_ZD_STOCK_EXCEPTION(1502, "中登股东账户查询异常"),

    /**
     * 三方存管信息提交异常
     */
    TP_BANK_EXCEPTION(1601, "三方存管信息提交异常"),

    /**
     * 设置密码异常
     */
    SET_PWD_EXCEPTION(1701, "设置密码异常"),

    /**
     * 风险测评提交异常
     */
    RISK_SURVEY_EXCEPTION(1801, "风险测评提交异常"),

    /**
     * 风险测评查询异常
     */
    RISK_SURVEY_GET_EXCEPTION(1802, "风险测评查询异常"),

    /**
     * 协议签署异常
     */
    SIGN_PROTOCOL_EXCEPTION(1901, "协议签署异常"),

    /**
     * 视频见证异常
     */
    WITNESS_EXCEPTION(2001, "视频见证异常"),

    /**
     * 视频见证驳回
     */
    WITNESS_REJECT(2002, "视频见证驳回"),

    /**
     * 回访问卷提交异常
     */
    VISIT_SURVEY_EXCEPTION(2101, "回访问卷提交异常"),

    /**
     * 提交开户申请异常
     */
    SUBMIT_SUCCESS_EXCEPTION(2201, "提交开户申请异常"),

    /**
     * 协议签署异常
     */
    PROTOCOL_SIGN_EXCEPTION(2301, "协议签署异常"),

    /**
     * 证书申请异常
     */
    APPLY_CERT_EXCEPTION(2401, "证书申请异常"),

    /**
     * 审核回调异常
     */
    AUDIT_CALLBACK_EXCEPTION(3001, "审核回调异常"),

    /**
     * 配置异常
     */
    SYSTEM_CONFIG_EXCEPTION(3101, "系统配置异常"),

    /**
     * 文件下载异常
     */
    FILE_DOWNLOAD_EXCEPTION(3201, "文件下载异常"),

    /**
     * 柜台信息查询异常
     */
    QUERY_EXCEPTION(3301, "柜台信息查询异常"),

    /**
     * 非法请求
     */
    REQUEST_ILLEGAL_EXCEPTION(9901, "非法请求"),

    /**
     * 用户信息不存在
     */
    USER_NON_EXIST_EXCEPTION(9902, "用户信息不存在");

    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误信息
     */
    private final String message;

    ResponseStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
