package com.example.demo.aliyun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.aliyuncs.utils.StringUtils;
import com.example.demo.exception.FileException;
import com.example.demo.exception.ImgException;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Random;

public class OSSUtil {
    public static final Logger logger = LoggerFactory.getLogger(OSSUtil.class);
    private OSSClient ossclient = OSSUtil.getOSSClient();
    private String bucketName = "uploadxxw";
    private String filedir = "picture/";
    public String KEY;

    public static OSSClient getOSSClient() {
        String endpoint = "oss-cn-beijing.aliyuncs.com";
         String accessKeyId = "LTAI4GDdLbAtMcaERcmR9kb7";
        String accessKeySecret = "SjnmkvrS6zkuQNmZ4J94DSTPSOUYRo";
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }


    public String uploadImg2Oss(MultipartFile file) throws ImgException {
        if (file.getSize() > 1 * 1024 * 1024) {
            throw new ImgException("上传图片大小不能超过1M！");
        }
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2OSS(inputStream, name);
            return name;
        } catch (Exception e) {
            throw new ImgException("图片上传失败");
        }
    }

    public String uploadFile2Oss(MultipartFile file) throws FileException {
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            ossclient.putObject(bucketName, filedir + name, file.getInputStream());
            return name;
        } catch (Exception e) {
            throw new FileException("文件上传失败");
        }
    }



    public String uploadFile2OSS(InputStream instream, String fileName) {
        String ret = "";
        try {
            // 创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);

            // 上传文件

            PutObjectResult putResult = ossclient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }


    /*
      Description: 判断OSS服务文件上传时文件的contentType

      @param FilenameExtension
                 文件后缀
      @return String
     */
    public static String getcontentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        else if (filenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        else if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
                || filenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        else if (filenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        else if (filenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        else if (filenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        else if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        else if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        else if (filenameExtension.equalsIgnoreCase("pdf")) {
            return "application/mspdf";
        }
        else if (filenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        else if (filenameExtension.equalsIgnoreCase("mp4")) {
            return "video/mp4";
        }
        else return "application/octet-stream";
    }

    public String getUploadUrl(String fileUrl) {
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            return this.getUrl(this.filedir + split[split.length - 1]);
        }
        return null;
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10

        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossclient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}