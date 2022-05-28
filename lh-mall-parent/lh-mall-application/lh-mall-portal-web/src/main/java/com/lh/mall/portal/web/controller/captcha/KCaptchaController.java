package com.lh.mall.portal.web.controller.captcha;

import com.baomidou.kaptcha.Kaptcha;
import com.lh.mall.portal.web.cutom.MyCustomGoogleKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type K captcha controller.
 * 通过拦截器，拦截特定的异常，然后进行统一处理。
 */
@RestController
@RequestMapping("/k-Captcha")
public class KCaptchaController {
    @Autowired
    private Kaptcha kaptcha;
    @Autowired
    MyCustomGoogleKaptcha mkaptcha;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

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
     * Generate code.
     * @param request  the request
     * @param response the response
     */
    @RequestMapping("/generateCodeRedis")
    public void generateCodeRedis(HttpServletRequest request, HttpServletResponse response) {
        kaptcha.render();
        String sessionKey = request.getSession().getId();
        String s = request.getSession().getAttribute("KAPTCHA_SESSION_KEY").toString();
        stringRedisTemplate.opsForValue().set(sessionKey, s);
    }

    @RequestMapping("/generateCodeRedisCustom")
    public void generateCodeRedisCustom(HttpServletRequest request, HttpServletResponse response) {
        mkaptcha.render();
    }

    @RequestMapping("/verifyCodeCustom")
    public String verifyCodeCustom(String code, HttpServletRequest request) {
        Boolean aBoolean = mkaptcha.validate(code);
        if (aBoolean) {
            // 可以这里验证后，直接remove掉。
            return "通过";
        }
        return "no";
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

    @RequestMapping("/verifyCodeRedis")
    public String verifyCodeRedis(String code, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String s = stringRedisTemplate.opsForValue().get(sessionId).toString();
        if (code.equals(s)) {
            // 可以这里验证后，直接remove掉。
            return "通过";
        }
        return "no";
    }
}
