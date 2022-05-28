package com.lh.mall.portal.web.controller.captcha;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/easy-Captcha")
public class EasyCaptchaController {

    /**
     * Generate code.
     */
    @RequestMapping("/generateCode")
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            CaptchaUtil.out(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/verifyCode")
    public String verifyCode(String code, HttpServletRequest request) {
        Boolean aBoolean = CaptchaUtil.ver(code, request);
        if (aBoolean) {
            // 可以这里验证后，直接remove掉。
            return "通过";
        }
        return "no";
    }
}
