package com.lh.mall.util.tess;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class Tess4j {

    public static void main(String[] args) throws TesseractException {
        ITesseract itesseract = new Tesseract();
        // 加载语言包
        itesseract.setDatapath("路径");
        // 设置语言
        itesseract.setLanguage("");
        // 把路径读出来
        File fileDir = new File("");
        for (File file : fileDir.listFiles()){
            String s = itesseract.doOCR(fileDir);
            System.out.println(file.getName() + s);
        }
    }
}
