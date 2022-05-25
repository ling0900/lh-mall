package com.lh.mall.portal.web.controller;

import com.lh.mall.util.code.ImageCode;
import com.lhcommon.base.annotation.TokenCheck;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * The type Verify code controller.
 */
@RestController
@RequestMapping("/code")
public class VerifyCodeController {

    String attrName = "verifyCode";

    /**
     * Generate code.
     */
    @RequestMapping("/generateCode")
    // @TokenCheck(required = false)
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        ImageCode imageCode = ImageCode.getInstance();
        // 得到code
        String code = imageCode.getCode();
        request.getSession().setAttribute(attrName, code);
        // 得到图片
        ByteArrayInputStream image = imageCode.getImage();
        // 写出去
        response.setContentType("image/jpeg");
        byte[] bytes = new byte[1024];
        try {
            ServletOutputStream out = response.getOutputStream();
            while (image.read(bytes) != -1) {
                out.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Verify code string.
     * @param code    the code
     * @param request the request
     * @return the string
     */
    @RequestMapping("/verifyCode")
    public String verifyCode(String code, HttpServletRequest request) {
        String s = request.getSession().getAttribute(attrName).toString();
        if (code.equals(s)) {
            return "通过";
        }
        return "no";
    }
}