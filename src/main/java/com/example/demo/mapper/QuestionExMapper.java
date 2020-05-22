package com.example.demo.mapper;

import com.example.demo.Model.Question;
import com.example.demo.dto.JqueryDTO;

import java.util.List;

public interface QuestionExMapper {
    int incView(Question record);
    int incComment(Question record);
    int countBySearch(JqueryDTO record);
    List<Question> selectRelate(Question question);
    List<Question> selectBySearch(JqueryDTO record);
}