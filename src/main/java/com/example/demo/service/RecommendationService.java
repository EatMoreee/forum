package com.example.demo.service;

import com.example.demo.Model.*;
import com.example.demo.dto.JqueryDTO;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.RecommendationDTO;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.RecommendExMapper;
import com.example.demo.mapper.RecommendMapper;
import com.example.demo.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private RecommendExMapper recommendExMapper;

    public PaginationDTO list(String search, Integer page, Integer size) {
        if(StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, " ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }

        JqueryDTO jqueryDTO = new JqueryDTO();
        jqueryDTO.setSearch(search);
        Integer totalCount = (int) recommendExMapper.countBySearch(jqueryDTO);
        Integer PageNum;
        if(totalCount % size == 0) PageNum = totalCount / size;
        else PageNum = totalCount / size + 1;
        if(page > PageNum) page = PageNum;
        if(page < 1) page = 1;

        Integer offset = (page - 1) * size;
        jqueryDTO.setPage(offset);
        jqueryDTO.setSize(size);
        List<Recommend> recommends = recommendExMapper.selectBySearch(jqueryDTO);
        List<RecommendationDTO> recommendationDTOS = new ArrayList<>();

        PaginationDTO<RecommendationDTO> paginationDTO = new PaginationDTO<>();
        for (Recommend recommend: recommends) {
            User user = userMapper.selectByPrimaryKey(recommend.getCreator());
            RecommendationDTO recommendationDTO = new RecommendationDTO();
            BeanUtils.copyProperties(recommend, recommendationDTO);
            recommendationDTO.setUser(user);
            recommendationDTOS.add(recommendationDTO);
        }
        paginationDTO.setData(recommendationDTOS);
        paginationDTO.setPagination(PageNum, page, size);
        return paginationDTO;
    }

    public PaginationDTO list(Long id, Integer page, Integer size) {
        RecommendExample recommendExample = new RecommendExample();
        recommendExample.createCriteria()
                .andCreatorEqualTo(id);
        Integer totalCount = (int) recommendMapper.countByExample(recommendExample);
        Integer PageNum;
        if(totalCount % size == 0) PageNum = totalCount / size;
        else PageNum = totalCount / size + 1;
        if(page > PageNum) page = PageNum;
        if(page < 1) page = 1;

        Integer offset = (page - 1) * size;
        RecommendExample example = new RecommendExample();
        example.setOrderByClause("gmt_create desc");
        example.createCriteria()
                .andCreatorEqualTo(id);
        List<Recommend> recommends = recommendMapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset,size));
        List<RecommendationDTO> recommendationDTOS = new ArrayList<>();

        PaginationDTO<RecommendationDTO> paginationDTO = new PaginationDTO<>();
        for (Recommend recommend: recommends) {
            User user = userMapper.selectByPrimaryKey(recommend.getCreator());
            RecommendationDTO recommendationDTO = new RecommendationDTO();
            BeanUtils.copyProperties(recommend, recommendationDTO);
            recommendationDTO.setUser(user);
            recommendationDTOS.add(recommendationDTO);
        }
        paginationDTO.setData(recommendationDTOS);
        paginationDTO.setPagination(PageNum, page, size);
        return paginationDTO;
    }

    public RecommendationDTO getById(Long id) {
        Recommend recommend = recommendMapper.selectByPrimaryKey(id);
        if (recommend == null) {
            throw  new CustomizeException(CustomizeErrorCode.RECOMMENDATION_NOT_FOUND);
        }
        RecommendationDTO recommendationDTO = new RecommendationDTO();
        User user = userMapper.selectByPrimaryKey(recommend.getCreator());
        recommendationDTO.setUser(user);
        BeanUtils.copyProperties(recommend, recommendationDTO);
        return recommendationDTO;
    }

    public List<RecommendationDTO> selectRelate(RecommendationDTO recommendationDTO) {
        if (StringUtils.isBlank(recommendationDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(recommendationDTO.getTag(),",|，");
        String regexTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Recommend recommend = new Recommend();
        recommend.setId(recommendationDTO.getId());
        recommend.setTag(regexTag);

        List<Recommend> recommends = recommendExMapper.selectRelate(recommend);
        List<RecommendationDTO> recommendationDTOS = recommends.stream().map(q -> {
            RecommendationDTO recommendationDTO1 = new RecommendationDTO();
            BeanUtils.copyProperties(q, recommendationDTO1);
            return recommendationDTO1;
        }).collect(Collectors.toList());
        return recommendationDTOS;
    }

    public void incView(Long id) {
        Recommend recommend = new Recommend();
        recommend.setId(id);
        recommend.setViewCount(1L);
        recommendExMapper.incView(recommend);
    }

    public void create(Recommend recommend) {
            recommend.setGmtCreate(System.currentTimeMillis());
            recommend.setGmtModified(recommend.getGmtCreate());
            recommend.setViewCount(0L);
            recommend.setCommentCount(0L);
            recommend.setLikeCount(0L);
            recommendMapper.insert(recommend);
    }

    public List<Recommend> HotList() {
        RecommendExample recommendExample = new RecommendExample();
        recommendExample.setOrderByClause("view_count desc");
        List<Recommend> recommends = recommendMapper.selectByExample(recommendExample);
        List<Recommend> recommendList = new ArrayList<>();
        for(int i = 0; i < recommends.size() && i < 10; ++i) {
            recommendList.add(recommends.get(i));
        }
        return recommendList;
    }

    public void incLike(Long id) {
        Recommend recommend = new Recommend();
        recommend.setId(id);
        recommend.setLikeCount(1L);
        recommendExMapper.incLike(recommend);
    }

    public void deleteById(Long id) {
        recommendMapper.deleteByPrimaryKey(id);
    }
}
