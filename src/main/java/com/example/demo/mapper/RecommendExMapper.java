package com.example.demo.mapper;

import com.example.demo.Model.Recommend;

import java.util.List;

public interface RecommendExMapper {
    int incView(Recommend record);
    int incComment(Recommend record);
    List<Recommend> selectRelate(Recommend recommend);
}
