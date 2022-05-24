package com.lh.mall.ums.entity.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author 99261
 * 注册的
 */
@Data
@ToString
public class UmsMemberRegisterDTO {
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
