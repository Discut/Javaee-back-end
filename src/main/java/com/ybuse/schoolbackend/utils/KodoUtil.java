package com.ybuse.schoolbackend.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * 图片上云
 */

public class KodoUtil {

    public static String accessKey = "oNlEbyVmdDzFv4cYjI9PYmvNnT_PMd7LH70p9_R9";
    public static String secretKey = "ErHhf7YmpsdaAabLFFjCB_u-Xw9gw1opo57VQAUp";
    public static String bucket = "ssmp";

    /**
     * 3、数据流上传 InputStream stream：数据流 <br/>
     * String key：文件名(桶里面的文件夹名称)，<br/>
     * - 默认不指定key的情况下，以文件内容的hash值作为文件名 <br/>
     * - 默认文件名存在桶中时，不覆盖<br/>
     * String token：认证凭证 <br/>
     * StringMap params： String mime：
     * <p>
     * http://rvmy28bqc.hn-bkt.clouddn.com/qr_images/bf651282-c1cf-484e-8506-60473d4862ba.png
     */
    public static String imagesUpload(String folderKey, String filePath, String uUid) {
//        String folderKey = "qr_images";
//        String filePath = "D:\\Javaee-course\\src\\main\\java\\com\\ybuse\\schoolbackend\\utils\\picture.jpg";
        //构造一个带指定 Region 对象的配置类，指定存储区域，和存储空间选择的区域一致
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = folderKey + "/" + uUid + "|" + System.currentTimeMillis() + ".png";
        FileInputStream fileInputStream = null;
        try {

//            String filePath = "C:\\Users\\Yang\\Desktop\\test1.jpg";
            fileInputStream = new FileInputStream(new File(filePath));
            //得到本地文件的字节数组
            byte[] bytes = IOUtils.toByteArray(fileInputStream);
            //byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
            //认证
            Auth auth = Auth.create(accessKey, secretKey);
            //认证通过后得到token（令牌）
            String upToken = auth.uploadToken(bucket);
            try {
                System.out.println("KodoUtils -------------- imagesUpload");
                //上传文件,参数：字节数组，key，token令牌
                //key: 建议我们自已生成一个不重复的名称
                Response response = uploadManager.put(bytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println("putRet.key -------->" + putRet.key);
                return putRet.key;
//                System.out.println(putRet.key);
//                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (IOException ex) {
            //ignore
        }
        return null;
    }


}
