package com.fk.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Author Yjw
 * 2022/11/10 23:18
 */
@Getter
@AllArgsConstructor
@ToString
public enum ResponseEnum {

    SUCCESS(0, "成功"),
    ERROR(-1, "失败"),

    SERVLET_ERROR(-102, "servlet请求异常"),
    ;

    //响应状态码
    private Integer code;
    //响应信息
    private String message;
}
