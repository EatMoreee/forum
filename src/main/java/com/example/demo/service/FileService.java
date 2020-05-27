package com.example.demo.service;

import com.example.demo.aliyun.OSSUtil;
import com.example.demo.exception.FileException;
import com.example.demo.exception.ImgException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    public String upDateImg(MultipartFile file) throws ImgException {
        if (file == null || file.getSize() <= 0) {
            throw new ImgException("file不能为空");
        }
        OSSUtil ossClient=new OSSUtil();
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getUploadUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }

    public String upDateFile(MultipartFile file) throws FileException {
        if (file == null || file.getSize() <= 0) {
            throw new FileException("file不能为空");
        }
        OSSUtil ossUtil = new OSSUtil();
        String name = ossUtil.uploadFile2Oss(file);
        String fileUrl = ossUtil.getUploadUrl(name);
        String[] split = fileUrl.split("\\?");
        return split[0];
    }
}
