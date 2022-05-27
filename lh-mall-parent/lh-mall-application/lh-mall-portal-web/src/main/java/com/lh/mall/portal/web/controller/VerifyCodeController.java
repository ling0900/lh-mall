package com.lh.mall.portal.web.controller;

import com.lh.mall.util.JCaptcha.JCaptchaUtil;
import com.lh.mall.util.code.ImageCode;
import com.lh.mall.util.code.ImageCodeMath;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

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
        ImageCodeMath imageCode = ImageCodeMath.getInstance();
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

    @RequestMapping("/generate64Code")
    // @TokenCheck(required = false)
    public String generate64Code(HttpServletRequest request, HttpServletResponse response) {
        String s = null;
        try {
            ImageCode imageCode = ImageCode.getInstance();
            // 得到code
            String code = imageCode.getCode();
            request.getSession().setAttribute(attrName, code);
            // 得到图片
            ByteArrayInputStream image = imageCode.getImage();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int r = 0;
            while ((r=image.read(buff, 0, 1024)) > 0) {
                swapStream.write(buff, 0, r);
            }

            byte[] data = swapStream.toByteArray();
            // 转64
            s = Base64.getEncoder().encodeToString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @RequestMapping("/generateJCCode")
    public void generateJCCode(HttpServletRequest request, HttpServletResponse response) {

        String sessionId = request.getSession().getId();
        ImageCaptchaService service = JCaptchaUtil.getService();
        BufferedImage bufferedImage = service.getImageChallengeForID(sessionId);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 转成图片类型
        JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(byteArrayOutputStream);

        try {
            jpegEncoder.encode(bufferedImage);

            // write
            response.setHeader("Cache-Control", "no-tore");
            response.setContentType("image/jpeg");
            byte[] bytes = byteArrayOutputStream.toByteArray();

            ServletOutputStream outputStream = response.getOutputStream();

            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
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

    @RequestMapping("/verifyJCCode")
    public String verifyJCCode(String code, HttpServletRequest request) {
        Boolean aBoolean = JCaptchaUtil.getService().validateResponseForID(request.getSession().getId(), code);
        if (aBoolean) {
            return "通过";
        }
        return "no";
    }
}
