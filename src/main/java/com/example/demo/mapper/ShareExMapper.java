package com.example.demo.mapper;

import com.example.demo.Model.Share;

import java.util.List;

public interface ShareExMapper {
    int incView(Share record);
    List<Share> selectRelate(Share share);
}
