package com.example.demo.mapper;

import com.example.demo.Model.Campus;
import com.example.demo.Model.CodeSolve;
import com.example.demo.dto.JqueryDTO;

import java.util.List;

public interface CodeSolveExMapper {
    int incView(CodeSolve record);
    List<CodeSolve> selectRelate(CodeSolve codeSolve);
    int countBySearch(JqueryDTO record);
    List<CodeSolve> selectBySearch(JqueryDTO record);
}
