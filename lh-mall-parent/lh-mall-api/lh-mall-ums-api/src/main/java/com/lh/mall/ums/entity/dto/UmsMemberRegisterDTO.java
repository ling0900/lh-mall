package com.lh.mall.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @author 99261
 * 注册的
 */
@Data
@ToString
public class UmsMemberRegisterDTO {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    private String password;
    /**
     * 头像
     */
    private String icon;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 昵称
     */
    private String nickName;
}
