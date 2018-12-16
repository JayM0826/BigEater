package com.bigeater.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:J on 2018/12/15.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BigEaterResult<T> {
    private int code;
    private String errMsg;
    private T data;

    public static <T> BigEaterResult<T> ofError(int code, String errMsg) {
        BigEaterResult<T> r = new BigEaterResult<T>();
        r.setCode(code);
        r.setErrMsg(errMsg);
        r.setData(null);
        return r;
    }

    public static <T> BigEaterResult<T> of(T data) {
        BigEaterResult<T> r = new BigEaterResult<T>();
        r.setCode(0);
        r.setErrMsg(null);
        r.setData(data);
        return r;
    }
}
