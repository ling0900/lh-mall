package com.lh.mall.portal.web.controller.captcha;

import com.lh.mall.portal.web.util.SliderUtil;
import com.lh.mall.portal.web.vo.VerificationVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
@RestController
@RequestMapping("/my-slider")
public class MySliderController {

    @GetMapping("/generator")
    public VerificationVO generatorCode(HttpServletRequest request, HttpServletResponse response) {

        return SliderUtil.cut();
    }

    @GetMapping("/verify")
    public String verify(String verifyCode, HttpServletRequest request) {

//		Boolean aBoolean = kaptcha.validate(verifyCode, 10);
//		if (aBoolean) {
//			return "通过";
//		}
        return "不通过";
    }
}

