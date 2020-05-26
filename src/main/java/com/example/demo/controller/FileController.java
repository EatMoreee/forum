package com.example.demo.controller;

import com.aliyun.oss.OSSClient;
import com.example.demo.Model.FileSave;
import com.example.demo.Model.User;
import com.example.demo.aliyun.OSSUtil;
import com.example.demo.dto.FileDTO;
import com.example.demo.exception.ImgException;
import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multiRequest.getFile("editormd-image-file");
        try {
            String url = fileService.updateHead(file);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(1);
            fileDTO.setUrl(url);
            return fileDTO;
        }catch (ImgException e) {
            System.out.println(e.getMessage());
        }
        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setUrl("/images/sea.jpg");
        return fileDTO;
    }

}
