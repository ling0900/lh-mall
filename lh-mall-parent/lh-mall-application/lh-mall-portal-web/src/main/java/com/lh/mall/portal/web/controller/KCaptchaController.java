package com.lh.mall.portal.web.controller;

import com.baomidou.kaptcha.Kaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type K captcha controller.
 */
@RestController
@RequestMapping("/k-Captcha")
public class KCaptchaController {
    @Autowired
    private Kaptcha kaptcha;

    /**
     * Generate code.
     * @param request  the request
     * @param response the response
     */
    @RequestMapping("/generateCode")
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        kaptcha.render();
    }

    /**
     * Verify code string.
     * @param code    the code
     * @param request the request
     * @return the string
     */
    @RequestMapping("/verifyCode")
    public String verifyCode(String code, HttpServletRequest request) {
        Boolean aBoolean = kaptcha.validate(code);
        if (aBoolean) {
            // 可以这里验证后，直接remove掉。
            return "通过";
        }
        return "no";
    }
}
