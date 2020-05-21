package com.example.demo.mapper;

import com.example.demo.Model.Campus;

import java.util.List;

public interface CampusExMapper {
    int incView(Campus record);
    List<Campus> selectRelate(Campus campus);
}
