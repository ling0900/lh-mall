package com.lh.mall.portal.web.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * 验证码vo 对象
 */
@Data
public class VerificationVO implements Serializable {


    /**
     *  滑块图
     */
    private String slidingImage;

    /**
     * 原图
     */
    private String originalImage;

    /**
     *  宽
     */
    private Integer xWidth;

    /**
     *  高
     */
    private Integer yHeight;

}
