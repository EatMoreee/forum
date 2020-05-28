package com.example.demo.service;

import com.example.demo.Model.User;
import com.example.demo.Model.UserExample;
import com.example.demo.mapper.UserExMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExMapper userExMapper;

    public int createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setEmpiricalValue(100L);
            user.setGrade(1);
            user.setState(0);
            userMapper.insert(user);
            return 1;
        }
        else {
            User dbuser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setName(user.getName());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setToken(user.getToken());
            updateUser.setEmpiricalValue(dbuser.getEmpiricalValue() + 10L);
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateUser,example);
            return 2;
        }
    }

    public void addGrade(int i, Long id) {
        User user = new User();
        user.setId(id);
        User dbUser = userMapper.selectByPrimaryKey(id);
        if (i == 1) {
            //发帖
            int grade = dbUser.getGrade();
            Long value = dbUser.getEmpiricalValue() + 20;
            if(value >= 1000) {
                grade = grade + 1;
                value = value % 1000;
            }
            user.setGrade(grade);
            user.setEmpiricalValue(value);
        }
        else if (i == 2) {
            //评论
            int grade = dbUser.getGrade();
            Long value = dbUser.getEmpiricalValue() + 3;
            if(value >= 1000) {
                grade = grade + 1;
                value = value % 1000;
            }
            user.setGrade(grade);
            user.setEmpiricalValue(value);
        }
        else if (i == 3) {
            //点赞
            int grade = dbUser.getGrade();
            Long value = dbUser.getEmpiricalValue() + 2;
            if(value >= 1000) {
                grade = grade + 1;
                value = value % 1000;
            }
            user.setGrade(grade);
            user.setEmpiricalValue(value);
        }
        if (user.getGrade() > 29) {
            user.setGrade(30);
            user.setEmpiricalValue(999L);
        }
        userExMapper.incGrade(user);
    }

    public void changeState(int state, Long id) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdEqualTo(id);
        User updateUser = new User();
        updateUser.setState(state);
        userMapper.updateByExampleSelective(updateUser, userExample);
    }
}
