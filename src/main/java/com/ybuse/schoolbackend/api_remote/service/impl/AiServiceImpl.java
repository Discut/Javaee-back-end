package com.ybuse.schoolbackend.api_remote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybuse.schoolbackend.api_remote.po.ImagePo;
import com.ybuse.schoolbackend.api_remote.service.IAiService;
import com.ybuse.schoolbackend.clazz.dao.IImageMapper;
import com.ybuse.schoolbackend.core.CustomException;
import com.ybuse.schoolbackend.utils.ExceptionUtil;
import com.ybuse.schoolbackend.utils.KodoUtil;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.sql.Timestamp;

/**
 * @Author: hyj
 * @Date: 2023/6/3
 * @Title: ---------
 * Description:
 */
@Service
public class AiServiceImpl extends ServiceImpl<IImageMapper, ImagePo> implements IAiService {

    final
    IImageMapper iImageMapper;

    @Value("${remote_server_url.resolution}")
    private String remoteServerUrl;

    @Value("${kodo_pre_url}")
    private String kodoPreUrl;

    public AiServiceImpl(IImageMapper iImageMapper) {
        this.iImageMapper = iImageMapper;
    }

    @Override
    public String superResolutionReconstruction(MultipartFile file) {
        try {
            String imageType = "jpg";
            WebClient webClient = WebClient.builder().build();
            // 构建请求体
            MultipartBodyBuilder builder = new MultipartBodyBuilder();
            val partBuilder = builder.part("file", file.getResource());
            val split = file.getName().split("\\.");
            val type = split[split.length - 1];
            if (imageType.equals(type)) {
                partBuilder.contentType(MediaType.IMAGE_JPEG);
            } else {
                partBuilder.contentType(MediaType.IMAGE_PNG);
            }
            // 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            // 发送请求
            Mono<String> response = webClient.post()
                    .uri(remoteServerUrl)
                    .headers(h -> h.addAll(headers))
                    .bodyValue(builder.build())
                    .retrieve()
                    .bodyToMono(String.class);
            String uUid = response.block();
            String localhostFilePath = "J:\\ssmp\\images\\" + uUid + ".png";
            String remoteUrl = kodoPreUrl + KodoUtil.imagesUpload("other_images", localhostFilePath, uUid);

            ImagePo imagePo = new ImagePo();
            imagePo.setImName(uUid);
            imagePo.setImPath(remoteUrl);
            imagePo.setCreateTime((new Timestamp(System.currentTimeMillis())));
            ExceptionUtil.isTrue(iImageMapper.insert(imagePo) < 1, "图片上传失败");

            return remoteUrl;
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

    }

    @Override
    public String personIdentification(InputStream photo) {
        return null;
    }

}
