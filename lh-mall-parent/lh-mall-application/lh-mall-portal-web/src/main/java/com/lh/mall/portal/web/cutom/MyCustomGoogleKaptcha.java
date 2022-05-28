/**
 * Copyright © 2018 TaoYu (tracy5546@gmail.com) modify by 孟令浩 992610900@qq.com Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy
 * of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.lh.mall.portal.web.cutom;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.exception.KaptchaIncorrectException;
import com.baomidou.kaptcha.exception.KaptchaNotFoundException;
import com.baomidou.kaptcha.exception.KaptchaRenderException;
import com.baomidou.kaptcha.exception.KaptchaTimeoutException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 谷歌默认验证码组件 个性化定制 redis 个性化定制 by 孟令浩
 * @author TaoYu
 */
@Slf4j
@Component
public class MyCustomGoogleKaptcha implements Kaptcha {

    public static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";
    public static final String KAPTCHA_SESSION_DATE = "KAPTCHA_SESSION_DATE";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private DefaultKaptcha kaptcha;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public MyCustomGoogleKaptcha(DefaultKaptcha kaptcha) {
        this.kaptcha = kaptcha;
    }

    @Override
    public String render() {
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-store, no-cache, must-revalidate");
        response.addHeader(HttpHeaders.CACHE_CONTROL, "post-check=0, pre-check=0");
        response.setHeader(HttpHeaders.PRAGMA, "no-cache");
        response.setContentType("image/jpeg");
        String sessionCode = kaptcha.createText();
        try (ServletOutputStream out = response.getOutputStream()) {
            redisTemplate.opsForValue().set(KAPTCHA_SESSION_KEY + ":" + request.getSession().getId(), sessionCode);
            redisTemplate.opsForValue().set(KAPTCHA_SESSION_DATE + ":" + request.getSession().getId(),
                System.currentTimeMillis());
            ImageIO.write(kaptcha.createImage(sessionCode), "jpg", out);
            return sessionCode;
        } catch (IOException e) {
            throw new KaptchaRenderException(e);
        }
    }

    @Override
    public boolean validate(String code) {
        return validate(code, 900);
    }

    @Override
    public boolean validate(@NonNull String code, long second) {
        HttpSession httpSession = request.getSession(false);
        String sessionCode;
        if (httpSession != null && (sessionCode = (String)httpSession.getAttribute(KAPTCHA_SESSION_KEY)) != null) {
            if (sessionCode.equalsIgnoreCase(code)) {
                long sessionTime =
                    (long)redisTemplate.opsForValue().get(KAPTCHA_SESSION_DATE + ":" + request.getSession().getId());
                long duration = (System.currentTimeMillis() - sessionTime) / 1000;
                if (duration < second) {
                    redisTemplate.delete(KAPTCHA_SESSION_KEY + ":" + request.getSession().getId());
                    redisTemplate.delete(KAPTCHA_SESSION_DATE + ":" + request.getSession().getId());
                    return true;
                } else {
                    throw new KaptchaTimeoutException();
                }
            } else {
                throw new KaptchaIncorrectException();
            }
        } else {
            throw new KaptchaNotFoundException();
        }
    }
}
