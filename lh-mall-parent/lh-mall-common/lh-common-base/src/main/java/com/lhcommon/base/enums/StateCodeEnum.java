package com.lhcommon.base.enums;

import lombok.Data;

/**
 * @author lh
 */

public enum StateCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(500, "内部错误");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    StateCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
