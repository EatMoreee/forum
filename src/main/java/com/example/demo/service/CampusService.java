package com.example.demo.service;

import com.example.demo.Model.*;
import com.example.demo.dto.*;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.CampusExMapper;
import com.example.demo.mapper.CampusMapper;
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
public class CampusService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CampusMapper campusMapper;

    @Autowired
    private CampusExMapper campusExMapper;

    public PaginationDTO list(String search, Integer page, Integer size) {
        if(StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, " ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }

        JqueryDTO jqueryDTO = new JqueryDTO();
        jqueryDTO.setSearch(search);
        Integer totalCount = (int) campusExMapper.countBySearch(jqueryDTO);
        Integer PageNum;
        if(totalCount % size == 0) PageNum = totalCount / size;
        else PageNum = totalCount / size + 1;
        if(page > PageNum) page = PageNum;
        if(page < 1) page = 1;

        Integer offset = (page - 1) * size;
        jqueryDTO.setPage(offset);
        jqueryDTO.setSize(size);
        List<Campus> campuses = campusExMapper.selectBySearch(jqueryDTO);
        List<CampusDTO> campusDTOS = new ArrayList<>();

        PaginationDTO<CampusDTO> paginationDTO = new PaginationDTO<>();
        for (Campus campus: campuses) {
            User user = userMapper.selectByPrimaryKey(campus.getCreator());
            CampusDTO campusDTO = new CampusDTO();
            BeanUtils.copyProperties(campus, campusDTO);
            campusDTO.setUser(user);
            campusDTOS.add(campusDTO);
        }
        paginationDTO.setData(campusDTOS);
        paginationDTO.setPagination(PageNum, page, size);
        return paginationDTO;
    }

    public PaginationDTO list(Long id, Integer page, Integer size) {
        CampusExample campusExample = new CampusExample();
        campusExample.createCriteria()
                .andCreatorEqualTo(id);
        Integer totalCount = (int) campusMapper.countByExample(campusExample);
        Integer PageNum;
        if(totalCount % size == 0) PageNum = totalCount / size;
        else PageNum = totalCount / size + 1;
        if(page > PageNum) page = PageNum;
        if(page < 1) page = 1;

        Integer offset = (page - 1) * size;
        CampusExample example = new CampusExample();
        example.setOrderByClause("gmt_create desc");
        example.createCriteria()
                .andCreatorEqualTo(id);
        List<Campus> campuses = campusMapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset,size));
        List<CampusDTO> campusDTOS = new ArrayList<>();

        PaginationDTO<CampusDTO> paginationDTO = new PaginationDTO<>();
        for (Campus campus : campuses) {
            User user = userMapper.selectByPrimaryKey(campus.getCreator());
            CampusDTO campusDTO = new CampusDTO();
            BeanUtils.copyProperties(campus, campusDTO);
            campusDTO.setUser(user);
            campusDTOS.add(campusDTO);
        }
        paginationDTO.setData(campusDTOS);
        paginationDTO.setPagination(PageNum, page, size);
        return paginationDTO;
    }

    public void create(Campus campus) {
            campus.setGmtCreate(System.currentTimeMillis());
            campus.setViewCount(0L);
            campus.setLikeCount(0L);
            campus.setCommentCount(0L);
            campusMapper.insert(campus);
    }


    public CampusDTO getById(Long id) {
        Campus campus = campusMapper.selectByPrimaryKey(id);
        if (campus == null) {
            throw  new CustomizeException(CustomizeErrorCode.RECORD_NOT_FOUND);
        }
        CampusDTO campusDTO = new CampusDTO();
        User user = userMapper.selectByPrimaryKey(campus.getCreator());
        campusDTO.setUser(user);
        BeanUtils.copyProperties(campus, campusDTO);
        return campusDTO;
    }

    public List<CampusDTO> selectRelate(CampusDTO campusDTO) {
        if (StringUtils.isBlank(campusDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(campusDTO.getTag(),",|，");
        String regexTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Campus campus = new Campus();
        campus.setId(campusDTO.getId());
        campus.setTag(regexTag);

        List<Campus> campuses = campusExMapper.selectRelate(campus);
        List<CampusDTO> campusDTOS = campuses.stream().map(q -> {
            CampusDTO campusDTO1 = new CampusDTO();
            BeanUtils.copyProperties(q, campusDTO1);
            return campusDTO1;
        }).collect(Collectors.toList());
        return campusDTOS;
    }

    public void incView(Long id) {
        Campus campus = new Campus();
        campus.setId(id);
        campus.setViewCount(1L);
        campusExMapper.incView(campus);
    }

    public List<Campus> HotList() {
        CampusExample campusExample = new CampusExample();
        campusExample.setOrderByClause("view_count desc");
        List<Campus> campuses = campusMapper.selectByExample(campusExample);
        List<Campus> campusList = new ArrayList<>();
        for(int i = 0; i < campuses.size() && i < 10; ++i) {
            campusList.add(campuses.get(i));
        }
        return campusList;
    }


    public void incLike(Long id) {
        Campus campus = new Campus();
        campus.setId(id);
        campus.setLikeCount(1L);
        campusExMapper.incLike(campus);
    }

    public void deleteById(Long id) {
        campusMapper.deleteByPrimaryKey(id);
    }
}
