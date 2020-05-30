package com.example.demo.service;

import com.example.demo.Model.Question;
import com.example.demo.Model.QuestionExample;
import com.example.demo.Model.User;
import com.example.demo.dto.JqueryDTO;
import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.QuestionExMapper;
import com.example.demo.mapper.QuestionMapper;
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
public class QuestionService {

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private QuestionExMapper questionExMapper;

    public PaginationDTO list(String search, Integer page, Integer size) {
        if(StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, " ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }

        JqueryDTO jqueryDTO = new JqueryDTO();
        jqueryDTO.setSearch(search);
        Integer totalCount = (int) questionExMapper.countBySearch(jqueryDTO);
        Integer PageNum;
        if(totalCount % size == 0) PageNum = totalCount / size;
        else PageNum = totalCount / size + 1;
        if(page > PageNum) page = PageNum;
        if(page < 1) page = 1;

        Integer offset = (page - 1) * size;
        jqueryDTO.setPage(offset);
        jqueryDTO.setSize(size);
        List<Question> questions = questionExMapper.selectBySearch(jqueryDTO);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
       for (Question question: questions) {
           User user = userMapper.selectByPrimaryKey(question.getCreator());
           QuestionDTO questionDTO = new QuestionDTO();
           BeanUtils.copyProperties(question, questionDTO);
           questionDTO.setUser(user);
           questionDTOList.add(questionDTO);
       }
       paginationDTO.setData(questionDTOList);
       paginationDTO.setPagination(PageNum, page, size);
       return paginationDTO;
    }

    public PaginationDTO list(Long id, Integer page, Integer size) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(id);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
        Integer PageNum;
        if(totalCount % size == 0) PageNum = totalCount / size;
        else PageNum = totalCount / size + 1;
        if(page > PageNum) page = PageNum;
        if(page < 1) page = 1;

        Integer offset = (page - 1) * size;
        QuestionExample example = new QuestionExample();
        example.setOrderByClause("gmt_create desc");
        example.createCriteria()
                .andCreatorEqualTo(id);
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        for (Question question: questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        paginationDTO.setPagination(PageNum, page, size);
        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw  new CustomizeException(CustomizeErrorCode.Question_Not_Found);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        BeanUtils.copyProperties(question, questionDTO);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null || question.getId() == 0) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0L);
            question.setCommentCount(0L);
            question.setLikeCount(0L);
            questionMapper.insert(question);
        }
        else {
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            int update = questionMapper.updateByExampleSelective(updateQuestion, questionExample);
            if (update != 1) {
                throw new CustomizeException(CustomizeErrorCode.Question_Not_Found);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1L);
        questionExMapper.incView(question);
    }

    public List<QuestionDTO> selectRelate(QuestionDTO questionDTO) {
        if (StringUtils.isBlank(questionDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(questionDTO.getTag(),",|，");
        String regexTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setTag(regexTag);

        List<Question> questions = questionExMapper.selectRelate(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO1 = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO1);
            return questionDTO1;
        }).collect(Collectors.toList());
        return questionDTOS;
    }

    public List<Question> HotList() {
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("view_count desc");
        List<Question> questions = questionMapper.selectByExample(questionExample);
        List<Question> questionList = new ArrayList<>();
        for(int i = 0; i < questions.size() && i < 10; ++i) {
            questionList.add(questions.get(i));
        }
        return questionList;
    }

    public List<Question> searchKey(String search) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("view_count desc");
        List<Question> questions = questionMapper.selectByExample(questionExample);
        List<Question> questionList = new ArrayList<>();
        for(Question question : questions) {
            if(question.getTitle().indexOf(search) != -1) {
                questionList.add(question);
            }
            else if (question.getDescription().indexOf(search) != -1) {
                questionList.add(question);
            }
            else if (question.getTag().indexOf(search) != -1) {
                questionList.add(question);
            }
        }
        return questionList;
    }

    public void incLike(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setLikeCount(1L);
        questionExMapper.incLike(question);
    }

    public void deleteById(Long id) {
        questionMapper.deleteByPrimaryKey(id);
    }
}
