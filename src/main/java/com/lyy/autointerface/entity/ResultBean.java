package com.lyy.autointerface.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean<T> implements Serializable {
    // 处理成功的状态码
    public static final String SUCCESS_CODE = "200";
    // 发生未知错误的状态码
    public static final String UNSPECIFIED_CODE = "500";

    private String message = "success";
    private String code = ResultBean.SUCCESS_CODE;

    private T data;

    public ResultBean(String msg, String code) {
        this.message = msg;
        this.code = code;
    }

    public ResultBean(T data) {
        this.data = data;
    }

    public ResultBean(String code, T data){
        this.code = code;
        this.data = data;
    }

    /**
     * 系统发生未知异常
     * @param e
     */
    public ResultBean(Throwable e) {
        super();
        this.message = "发生未知错误，请稍后重试!";
        this.code = ResultBean.UNSPECIFIED_CODE;
    }
}
