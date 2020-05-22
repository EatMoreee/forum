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
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private UserExMapper userExMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
        else {
            User dbuser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setName(user.getName());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateUser,example);
        }
    }

    public void addGrade(int i, Long id) {
        User user = new User();
        user.setId(id);
        User dbUser = userMapper.selectByPrimaryKey(id);
        if (i == 1) {
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
            int grade = dbUser.getGrade();
            Long value = dbUser.getEmpiricalValue() + 1;
            if(value >= 1000) {
                grade = grade + 1;
                value = value % 1000;
            }
            user.setGrade(grade);
            user.setEmpiricalValue(value);
        }
        userExMapper.incGrade(user);
    }
}
