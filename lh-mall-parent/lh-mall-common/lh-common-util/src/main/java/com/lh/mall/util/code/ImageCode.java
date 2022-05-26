package com.lh.mall.util.code;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * The type Image code.
 */
@Data
public class ImageCode {
    private String code;
    private ByteArrayInputStream image;
    // 图形的属性
    private int width = 100;
    private int height =  80;

    /**
     * Get instance image code.
     *生成一个图，这个单例需要优化。
     * @return the image code
     */
    public static ImageCode getInstance(){
        return new ImageCode();
    }

    private ImageCode() {
        // 图形缓冲区，类似于黑板。
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 画笔
        Graphics graphics = image.getGraphics();
        // 画图
        graphics.setColor(new Color(100, 200, 22));
        // 矩形
        graphics.fillRect(0, 0, width, height);
        //开始涂字，字体
        graphics.setFont(new Font("楷体", Font.BOLD, 30));
        // 数字
        Random random = new Random();
        this.code = "";
        for (int i = 0; i < 4; i++) {
            String s = String.valueOf(random.nextInt(10));
            this.code += s;
            // 字的颜色
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawString(s, (width / 6) * i, 40);

            // 画线
            graphics.setColor(new Color(100, 100, 100));
            graphics.drawLine((width / 6) * i, 40, (width / 6) * i + 22, 40 - 12);
        }



        // 结束
        graphics.dispose();

        ByteOutputStream out = new ByteOutputStream();
        ByteArrayInputStream in = null;
        // 赋值给bytearrayinputstream
        try {
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(out);
            ImageIO.write(image, "jpeg", imageOutputStream);
            in = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = in;
    }
}
