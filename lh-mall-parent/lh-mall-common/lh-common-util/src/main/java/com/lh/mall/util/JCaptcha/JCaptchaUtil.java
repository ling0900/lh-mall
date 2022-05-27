package com.lh.mall.util.JCaptcha;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JCaptchaUtil {

    // 饿汉式单例，简单，不怕占用存储空间。
    private static final ImageCaptchaService SERVICE = imageCaptchaService();
    public static ImageCaptchaService getService() {
        return SERVICE;
    }

    private static ImageCaptchaService imageCaptchaService () {
        log.info("单例，恶汉成功了！！！！！");
        // 背景
        UniColorBackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(100, 50);

        // 字的颜色
        RandomRangeColorGenerator textColor
                = new RandomRangeColorGenerator(new int[]{0, 100}, new int[]{100, 200}, new int[]{0, 100});
        RandomTextPaster randomTextPaster
                = new RandomTextPaster(3, 5, textColor);
        RandomFontGenerator randomFontGenerator
                = new RandomFontGenerator(20,40);
        // 拼图
        ComposedWordToImage composedWordToImage
                = new ComposedWordToImage(randomFontGenerator, backgroundGenerator, randomTextPaster);

        ComposeDictionaryWordGenerator composeDictionaryWordGenerator
                = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));

        GimpyFactory gimpyFactory
                = new GimpyFactory(composeDictionaryWordGenerator, composedWordToImage);
        GenericCaptchaEngine genericCaptchaEngine
                = new GenericCaptchaEngine(new CaptchaFactory[]{gimpyFactory});
        return new GenericManageableCaptchaService(genericCaptchaEngine, 20, 3000, 3000);
    }
}
