package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.*;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer Page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer PageNum, Integer page, Integer size) {
        Page = page;
        totalPage = PageNum;
        int start = max(1, page-3);
        int end = min(PageNum, page+3);
        if (end < 1) end = 1;
        for(int i = start; i <= end; ++i) pages.add(i);

        if (page == 1) showPrevious = false;
        else showPrevious = true;
        if (page >= PageNum) showNext = false;
        else showNext = true;
        if (pages.contains(1)) showFirstPage = false;
        else showFirstPage = true;
        if (pages.contains(PageNum) || PageNum < page) showEndPage = false;
        else showEndPage = true;
    }
}
