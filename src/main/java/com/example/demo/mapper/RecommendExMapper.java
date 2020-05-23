package com.example.demo.mapper;

import com.example.demo.Model.Campus;
import com.example.demo.Model.Recommend;
import com.example.demo.dto.JqueryDTO;

import java.util.List;

public interface RecommendExMapper {
    int incView(Recommend record);
    int incLike(Recommend record);
    int incComment(Recommend record);
    List<Recommend> selectRelate(Recommend recommend);
    int countBySearch(JqueryDTO record);
    List<Recommend> selectBySearch(JqueryDTO record);
}
