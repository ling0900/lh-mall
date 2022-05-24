package com.lhcommon.base.result;

import com.lhcommon.base.enums.StateCodeEnum;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lh
 */
@Data
@ToString
@Builder
public class ResultWrapper<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    // 成功
    public static ResultWrapper.ResultWrapperBuilder getSuccessResultWrapperBuilder(){
        return ResultWrapper.builder()
                .code(StateCodeEnum.SUCCESS.getCode())
                .msg(StateCodeEnum.SUCCESS.getMsg());
    }

    // 失败
    public static ResultWrapper.ResultWrapperBuilder getFailedResultWrapperBuilder(){
        return ResultWrapper.builder()
                .code(StateCodeEnum.FAIL.getCode())
                .msg(StateCodeEnum.FAIL.getMsg());
    }
}
