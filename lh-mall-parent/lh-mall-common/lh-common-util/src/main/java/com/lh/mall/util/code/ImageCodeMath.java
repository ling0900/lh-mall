package com.lh.mall.util.code;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * The type Image code.
 */
@Data
public class ImageCodeMath {
    private String code;
    private ByteArrayInputStream image;
    // 图形的属性
    private int width = 400;
    private int height =  100;

    /**
     * Get instance image code.
     *生成一个图，这个单例需要优化。
     * @return the image code
     */
    public static ImageCodeMath getInstance(){
        return new ImageCodeMath();
    }

    private ImageCodeMath() {
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
        // 定义两个数
        int m = random.nextInt(30);
        int n = random.nextInt(30);
        graphics.setColor(Color.magenta);
        graphics.drawString(m + "", (width/6)*0+2, 30);
        graphics.drawString("+", (width/6)*1+2, 30);
        graphics.drawString(n + "", (width/6)*2+2, 30);
        graphics.drawString("=", (width/6)*3+2, 30);
        graphics.drawString("?", (width/6)*4+2, 30);
        // 将结果记录下来
        int resultCode = m + n;
        this.code = resultCode + "";
        // 干扰线条
        graphics.setColor(Color.yellow);
        for (int i = 0; i < 9; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(20);
            int y1 = random.nextInt(20);
            graphics.drawLine(x, y, x + x1, y + y1);
        }
        // 验证码生成结束
        graphics.dispose();
        // 开始输出验证码
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
