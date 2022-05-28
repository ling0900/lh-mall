package com.lh.mall.portal.web.advice;

import com.baomidou.kaptcha.exception.KaptchaException;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.lhcommon.base.exception.TokenExcepiton;
import com.lhcommon.base.result.ResultWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*@ControllerAdvice
// 不加@RestController，则捕获异常时候，找不到页面：404.
@RestController*/

/**
 * @author lh
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    //这里精确异常
    @ExceptionHandler(NullPointerException.class)
    public ResultWrapper cutomException() {
        // 思考，如何定位到是哪里的异常？
        return ResultWrapper.builder().code(0000).msg("null").build();
    }
    // 捕获TokenExcepiton异常
    @ExceptionHandler(TokenExcepiton.class)
    public ResultWrapper lockException(Exception e) {
        return ResultWrapper.getFailedResultWrapperBuilder()
                .msg(e.getMessage())
                .build();
    }

    //
    @ExceptionHandler(KaptchaException.class)
    public String kCaptchaException(KaptchaException e) {
        if (e instanceof KaptchaTimeoutException) {
            return "超时";
        } else if (e instanceof KaptchaIncorrectException) {
            return "验证码错误";
        } else if (e instanceof KaptchaNotFoundException) {
            return "wu";
        }
        return "";
    }
}
