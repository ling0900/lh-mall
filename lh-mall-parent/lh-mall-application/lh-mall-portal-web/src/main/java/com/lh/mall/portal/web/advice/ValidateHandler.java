package com.lh.mall.portal.web.advice;

import com.lhcommon.base.result.ResultWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// 校验处理类，这个怎么实现的呢，底层？
@ControllerAdvice
public class ValidateHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers, HttpStatus status, WebRequest request) {

        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            String defaultMessage = fieldError.getDefaultMessage();
            sb.append(" " + defaultMessage);
            //有一个异常就抛出
            break;
        }
        return new ResponseEntity(ResultWrapper.builder()
                .code(100)
                .msg(sb.toString()).build(), HttpStatus.OK);
    }
}
