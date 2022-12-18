package com.fk.common.result;

import lombok.Data;

/**
 * Author Yjw
 * 2022/11/10 23:17
 */
@Data
public class R<T> {

    //返回码
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private T data;

    public R(){}

    // 返回数据
    public static <T> R<T> build(T data) {
        R<T> r = new R<T>();
        if (data != null)
            r.setData(data);
        return r;
    }

    public static <T> R<T> build(T body, Integer code, String message) {
        R<T> r = build(body);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static <T> R<T> build(T body, ResponseEnum responseEnum) {
        R<T> r = build(body);
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }

    public static<T> R<T> ok(){
        return R.ok(null);
    }

    /**
     * 操作成功
     * @param data  baseCategory1List
     * @param <T>
     * @return
     */
    public static<T> R<T> ok(T data){
        R<T> result = build(data);
        return build(data, ResponseEnum.SUCCESS);
    }

    public static<T> R<T> fail(){
        return R.fail(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> R<T> fail(T data){
        R<T> r = build(data);
        return build(data, ResponseEnum.ERROR);
    }

    public R<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public R<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}
