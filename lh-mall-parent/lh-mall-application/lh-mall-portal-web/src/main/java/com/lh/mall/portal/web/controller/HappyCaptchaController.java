package com.lh.mall.portal.web.controller;

import com.ramostear.captcha.HappyCaptcha;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/happy-Captcha")
public class HappyCaptchaController {

    String attrName = "verifyCode";

    /**
     * Generate code.
     */
    @RequestMapping("/generateCode")
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        HappyCaptcha.require(request, response).build().finish();


    }

    @RequestMapping("/verifyCode")
    public String verifyCode(String code, HttpServletRequest request) {
        Boolean aBoolean = HappyCaptcha.verification(request, code, true);
        if (aBoolean) {
            return "通过";
        }
        return "no";
    }
}
