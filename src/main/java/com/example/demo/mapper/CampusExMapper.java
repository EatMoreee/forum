package com.example.demo.mapper;

import com.example.demo.Model.Campus;
import com.example.demo.dto.JqueryDTO;

import java.util.List;

public interface CampusExMapper {
    int incView(Campus record);
    int incLike(Campus campus);
    List<Campus> selectRelate(Campus campus);
    int countBySearch(JqueryDTO record);
    List<Campus> selectBySearch(JqueryDTO record);
}
