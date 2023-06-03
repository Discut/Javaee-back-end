/*
 * Copyright (c) 2021 WEI.ZHOU. All rights reserved.
 * The following code is only used for learning and communication, not for illegal and
 * commercial use.
 * If the code is used, no consent is required, but the author has nothing to do with any problems
 * and consequences.
 * In case of code problems, feedback can be made through the following email address.
 *
 *                        <xiaoandx@gmail.com>
 */
package com.ybuse.schoolbackend.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * <p> 二维码生成 </p>
 *
 * @version V1.0.0
 * @ClassName:QrCoder
 * @author: WEI.ZHOU
 * @date: 2021-12-24 09:32
 * @since: JDK1.8
 */
public class QrCoder {

    /**
     * 图片的宽度
     */
    private static final int IMAGE_WIDTH = 700;
    /**
     * 图片的高度(需按实际内容高度进行调整)
     */
    private static final int IMAGE_HEIGHT = 700;
    /**
     * 二维码的宽度
     */
    private static final int QR_CODE_WIDTH = 530;
    /**
     * 二维码的宽度
     */
    private static final int QR_CODE_HEIGHT = 530;

    /**
     * 图片后缀
     */
    private static final String FORMAT_NAME = "JPG";

    /*
     *  设计生成二维码图片
     * @author WEI.ZHOU
     * @version v1.0.0
     * @date 2021-12-24 10:51
     * @param: imgLogo      logo图片
     * @param: title        头部标题
     * @param: contentInfo  二维码介绍
     * @param: content      二维码内容
     * @param: footer       二维码底部说明
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage createQrCode(String imgLogo, String title, String contentInfo, String content, String footer) {
        // 头部文字区域高度
        int titleHeight = 50;

        // 创建主模板图片
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D main = image.createGraphics();
        // 设置图片的背景色
        main.setColor(Color.white); //白色
        main.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

        // 动态高度
        int height = 0;

        //***********************页面头部 文字****************
        Graphics2D titleRight = image.createGraphics();
        // 设置字体颜色 black黑 white白
        titleRight.setColor(Color.black);
        // 设置字体
        Font titleFont = new Font("宋体", Font.BOLD, 30);
        titleRight.setFont(titleFont);
        titleRight.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        // 居中 x开始的位置：（图片宽度-字体大小*字的个数）/2
        int x = (IMAGE_WIDTH - (titleFont.getSize() * title.length())) / 2;
        titleRight.drawString(title, x, (titleHeight) / 2 + 30);
        height += titleHeight;

        //**********************中间文字部分*********
        Graphics2D centerWord = image.createGraphics();
        // 设置字体颜色，先设置颜色，再填充内容
        centerWord.setColor(Color.black);
        // 设置字体
        Font wordFont = new Font("宋体", Font.PLAIN, 25);
        centerWord.setFont(wordFont);
        centerWord.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        String[] info = contentInfo.split("-");
        for (String s : info) {
            // x开始的位置：（图片宽度-字体大小*字的个数）/2
            int strWidth = centerWord.getFontMetrics().stringWidth(s);
            // 总长度减去文字长度的一半  （居中显示）
            int startX = (IMAGE_WIDTH - strWidth) / 2;
            height += 40;
            centerWord.drawString(s, startX, height);
        }

        //***************插入二维码图片***********************************************
        Graphics codePic = image.getGraphics();
        BufferedImage codeImg;
        QrConfig config = new QrConfig();
        config.setWidth(QR_CODE_WIDTH);
        config.setHeight(QR_CODE_HEIGHT);
        if (StrUtil.isNotBlank(imgLogo)) {
            config.setImg(imgLogo);
        }
        codeImg = QrCodeUtil.generate(content, config);
        // 绘制二维码
        codePic.drawImage(codeImg, (IMAGE_WIDTH - QR_CODE_WIDTH) / 2, height, QR_CODE_WIDTH, QR_CODE_HEIGHT, null);
        codePic.dispose();

        //**********************底部公司名字*********
        Graphics2D typeLeft = image.createGraphics();
        // 设置字体颜色
        typeLeft.setColor(Color.black);
        // 设置字体
        Font footerFont = new Font("宋体", Font.PLAIN, 20);
        typeLeft.setFont(footerFont);
        typeLeft.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        // x开始的位置：（图片宽度-字体大小*字的个数）/2
        int startX = (IMAGE_WIDTH - (footerFont.getSize() * footer.length() - 320)) / 2;
        height += QR_CODE_HEIGHT + 15;
        typeLeft.drawString(footer, startX, height);

        // 返回image方便后续处理是生成图片还是生成base64字符串
        return image;
    }

    /*
     * 生成图片文件保存在本地
     * @author WEI.ZHOU
     * @version v1.0.0
     * @date 2021-12-24 10:51
     * @param: image
     * @param: fileLocation
     * @return void
     */
    public static void createImage(BufferedImage image, String fileLocation) {
        if (image != null) {
            try {
                ImageIO.write(image, "png", new File(fileLocation));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {
        String content = "站点名称：测试站点名称-站点地址：XXXXXXXXXXXXXXXXXXXXX";
        BufferedImage bufferedImage = createQrCode("", "垃圾站点二维码", content, "http://xiaoandx.club", "站点编号：f34bbf9320ff42618417df734ba2544c");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", os);
        InputStream input = new ByteArrayInputStream(os.toByteArray());


        createImage(bufferedImage, "D:/clean/images/qr/test3.png");
    }

}
