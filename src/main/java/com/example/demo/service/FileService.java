package com.example.demo.service;

import com.example.demo.Model.FileExample;
import com.example.demo.Model.FileSave;
import com.example.demo.Model.FileSaveExample;
import com.example.demo.aliyun.OSSUtil;
import com.example.demo.exception.ImgException;
import com.example.demo.mapper.FileSaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    public String updateHead(MultipartFile file) throws ImgException {
        if (file == null || file.getSize() <= 0) {
            throw new ImgException("file不能为空");
        }
        OSSUtil ossClient=new OSSUtil();
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }
}
