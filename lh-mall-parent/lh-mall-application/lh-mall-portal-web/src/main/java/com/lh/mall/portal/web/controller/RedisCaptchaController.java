package com.lh.mall.portal.web.controller;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/redis-Captcha")
public class RedisCaptchaController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Generate code.
     */
    @RequestMapping("/generateCode")
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            SpecCaptcha specCaptcha = new SpecCaptcha(100,60);
            String textCode = specCaptcha.text();
            // 放redis
            String uuid = "code-ca";
            //+ UUID.randomUUID();
            stringRedisTemplate.opsForValue().set(uuid, textCode);
            CaptchaUtil.out(specCaptcha, request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/verifyCode")
    public String verifyCode(String code, HttpServletRequest request) {
        String s = stringRedisTemplate.opsForValue().get("code-ca");
        if (s.equals(code)) {
            // 可以这里验证后，直接remove掉。
            return "通过";
        }
        return "no";
    }
}
