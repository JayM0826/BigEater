package com.bigeater.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author:J on 2018/12/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IllegalException extends RuntimeException {
    int code;
    String errMsg;
    HttpStatus httpStatus;
}
