package com.example.demo.mapper;

import com.example.demo.Model.Question;
import com.example.demo.dto.QuestionDTO;

import java.util.List;

public interface QuestionExMapper {
    int incView(Question record);
    int incComment(Question record);
    List<Question> selectRelate(Question question);
}