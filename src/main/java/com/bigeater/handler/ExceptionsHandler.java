package com.bigeater.handler;

import com.bigeater.exception.IllegalException;
import com.bigeater.vo.BigEaterResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author:J on 2018/12/16.
 */
@ControllerAdvice()
public class ExceptionsHandler {
    @ExceptionHandler(IllegalException.class)
    ResponseEntity exceptionHandler(IllegalException e) {
        return new ResponseEntity(BigEaterResult.ofError(e.getCode(), e.getErrMsg()), e.getHttpStatus());
    }

}
