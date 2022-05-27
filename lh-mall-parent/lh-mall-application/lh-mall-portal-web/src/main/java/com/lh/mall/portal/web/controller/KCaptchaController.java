package com.lh.mall.portal.web.controller;

import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import com.ramostear.captcha.support.CaptchaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/k-Captcha")
public class KCaptchaController {

    String attrName = "verifyCode";

    /**
     * Generate code.
     */
    @RequestMapping("/generateCode")
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        HappyCaptcha.require(request, response)
                .style(CaptchaStyle.ANIM)
                // 设置样式，例如加减等等。
                .type(CaptchaType.ARITHMETIC_ZH)
                .build().finish();
    }

    @RequestMapping("/verifyCode")
    public String verifyCode(String code, HttpServletRequest request) {
        Boolean aBoolean = HappyCaptcha.verification(request, code, true);
        if (aBoolean) {
            // 可以这里验证后，直接remove掉。
            return "通过";
        }
        return "no";
    }
}
