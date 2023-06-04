package com.ybuse.schoolbackend.utils;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.ybuse.schoolbackend.clazz.domain.po.QrImagePo;
import org.springframework.util.ResourceUtils;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UuidGenerate {

    public static String qrURL;

    public static void UuidGenerate() throws IOException {

        String simpleUUID = IdUtil.simpleUUID();
        System.out.println(simpleUUID);

        //  获取指定目录的绝对地址
        String path = ResourceUtils.getURL("target/classes/public/images/qr").getPath();
        System.out.println(path);
        qrURL = path + simpleUUID + ".jpg";
        //  这个就是需要保存在数据库的相对地址
        String databaseQRURL = ResourceUtils.getURL("src/main/resources/public/images/qr").getPath() + simpleUUID + ".jpg";
        System.out.println(databaseQRURL);
        QrCodeUtil.generate("https://hutool.cn/", 300, 300, FileUtil.file(databaseQRURL));
        // 打开输入流
        FileInputStream fis = new FileInputStream(databaseQRURL);
        // 打开输出流
        FileOutputStream fos = new FileOutputStream(qrURL);

        // 读取和写入信息
        int len = 0;
        // 创建一个字节数组，当做缓冲区
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            fos.write(b, 0, len);
        }

        // 关闭流  先开后关  后开先关
        fos.close(); // 后开先关
        fis.close(); // 先开后关

    }

    public static String qrCode(String clientURL,String UUID, String fileSavePath){
        qrURL = fileSavePath +"qr/"+ UUID + ".jpg";
        //  这个就是需要保存在数据库的相对地址
        String databaseQRURL = "images/qr/"+ UUID + ".jpg";
        QrCodeUtil.generate(clientURL+"?siteUUID=" + UUID, 500, 500, FileUtil.file(qrURL));
        return databaseQRURL;
    }

    public static List<String> qrCodeByInfo(QrImagePo qrImagePo, String qrLogo, String clientUrl, String uUid, String fileSavePath, String kodoPreUrl){
        // 存储本地路径以及云路径
        List<String> filePaths = new ArrayList<>();
        qrURL = fileSavePath + "qr/" + uUid + ".png";
        //  这个就是需要保存在数据库的相对地址
//        String databaseQuery = "images/qr/" + uUid + ".png"; // IDEA中相对位置
        String siteInfo = qrImagePo.getQrTitle();
        String content = clientUrl + "?classUuid=" + uUid;
        BufferedImage bufferedImage = QrCoder.createQrCode(qrLogo, qrImagePo.getQrTitle(), siteInfo, content, qrImagePo.getQrFooter());
        // 本地地址 qrURL
        QrCoder.createImage(bufferedImage,qrURL);
        // 云地址
        String remoteUrl = kodoPreUrl + KodoUtil.imagesUpload("qr_images",qrURL,uUid);
        filePaths.add(qrURL);
        filePaths.add(remoteUrl);
        return filePaths;
    }

}
