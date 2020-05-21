package com.example.demo.mapper;

import com.example.demo.Model.CodeSolve;

import java.util.List;

public interface CodeSolveExMapper {
    int incView(CodeSolve record);
    List<CodeSolve> selectRelate(CodeSolve codeSolve);
}
