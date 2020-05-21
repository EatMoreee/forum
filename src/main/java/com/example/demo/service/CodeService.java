package com.example.demo.service;

import com.example.demo.Model.*;
import com.example.demo.dto.CodeSolveDTO;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.CodeSolveExMapper;
import com.example.demo.mapper.CodeSolveMapper;
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
public class CodeService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CodeSolveMapper codeSolveMapper;

    @Autowired
    private CodeSolveExMapper codeSolveExMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer totalCount = (int) codeSolveMapper.countByExample(new CodeSolveExample());
        Integer PageNum;
        if(totalCount % size == 0) PageNum = totalCount / size;
        else PageNum = totalCount / size + 1;
        if(page > PageNum) page = PageNum;
        if(page < 1) page = 1;

        Integer offset = (page - 1) * size;
        CodeSolveExample codeSolveExample = new CodeSolveExample();
        codeSolveExample.setOrderByClause("create_time desc");
        List<CodeSolve> codes = codeSolveMapper.selectByExampleWithRowbounds(codeSolveExample,new RowBounds(offset,size));
        List<CodeSolveDTO> codeSolveDTOS = new ArrayList<>();

        PaginationDTO<CodeSolveDTO> paginationDTO = new PaginationDTO<>();
        for (CodeSolve code: codes) {
            User user = userMapper.selectByPrimaryKey(code.getCreator());
            CodeSolveDTO codeSolveDTO = new CodeSolveDTO();
            BeanUtils.copyProperties(code, codeSolveDTO);
            codeSolveDTO.setUser(user);
            codeSolveDTOS.add(codeSolveDTO);
        }
        paginationDTO.setData(codeSolveDTOS);
        paginationDTO.setPagination(PageNum, page, size);
        return paginationDTO;
    }

    public CodeSolveDTO getById(Long id) {
        CodeSolve codeSolve = codeSolveMapper.selectByPrimaryKey(id);
        if (codeSolve == null) {
            throw  new CustomizeException(CustomizeErrorCode.SOLUTION_NOT_FOUND);
        }
        CodeSolveDTO codeSolveDTO = new CodeSolveDTO();
        User user = userMapper.selectByPrimaryKey(codeSolve.getCreator());
        codeSolveDTO.setUser(user);
        BeanUtils.copyProperties(codeSolve, codeSolveDTO);
        return codeSolveDTO;
    }

    public List<CodeSolveDTO> selectRelate(CodeSolveDTO codeSolveDTO) {
        if (StringUtils.isBlank(codeSolveDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(codeSolveDTO.getTag(),",|，");
        String regexTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        CodeSolve codeSolve = new CodeSolve();
        codeSolve.setId(codeSolveDTO.getId());
        codeSolve.setTag(regexTag);

        List<CodeSolve> codeSolves = codeSolveExMapper.selectRelate(codeSolve);
        List<CodeSolveDTO> codeSolveDTOS = codeSolves.stream().map(q -> {
            CodeSolveDTO code = new CodeSolveDTO();
            BeanUtils.copyProperties(q, code);
            return code;
        }).collect(Collectors.toList());
        return codeSolveDTOS;
    }

    public void incView(Long id) {
        CodeSolve codeSolve = new CodeSolve();
        codeSolve.setId(id);
        codeSolve.setViewCount(1L);
        codeSolveExMapper.incView(codeSolve);
    }

    public void createOrUpdate(CodeSolve codeSolve) {
        if (codeSolve.getId() == null) {
            codeSolve.setCreateTime(System.currentTimeMillis());
            codeSolve.setViewCount(0L);
            codeSolve.setLikeCount(0L);
            codeSolveMapper.insert(codeSolve);
        }
        else {
            CodeSolve updateCode = new CodeSolve();
            updateCode.setCreateTime(System.currentTimeMillis());
            updateCode.setTitle(codeSolve.getTitle());
            updateCode.setDescription(codeSolve.getDescription());
            updateCode.setTag(codeSolve.getTag());
            CodeSolveExample codeSolveExample = new CodeSolveExample();
            codeSolveExample.createCriteria()
                    .andIdEqualTo(codeSolve.getId());
            int update = codeSolveMapper.updateByExampleSelective(updateCode, codeSolveExample);
            if (update != 1) {
                throw new CustomizeException(CustomizeErrorCode.SOLUTION_NOT_FOUND);
            }
        }
    }
}
