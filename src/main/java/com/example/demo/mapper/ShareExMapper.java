package com.example.demo.mapper;

import com.example.demo.Model.Campus;
import com.example.demo.Model.Share;
import com.example.demo.dto.JqueryDTO;

import java.util.List;

public interface ShareExMapper {
    int incView(Share record);
    int incLike(Share record);
    int incDownload(Share record);
    List<Share> selectRelate(Share share);
    int countBySearch(JqueryDTO record);
    List<Share> selectBySearch(JqueryDTO record);


}
