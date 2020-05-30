package com.example.demo.service;

import com.example.demo.Model.User;
import com.example.demo.Model.UserExample;
import com.example.demo.mapper.UserExMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
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
                .andNameEqualTo(user.getName());
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
            updateUser.setToken(user.getToken());
            int grade = dbuser.getGrade();
            long value = dbuser.getEmpiricalValue() + 10;
            if (value >= 1000) {
                grade = grade + 1;
                value = value % 1000;
            }
            if (grade > 29) {
                grade = 30;
                value = 999L;
            }
            updateUser.setEmpiricalValue(value);
            updateUser.setGrade(grade);
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

    public boolean login(String name, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() <= 0 || users == null) return false;
        if (users.get(0).getPassword().equals(password)) return true;
        return false;
    }

    public boolean register(String name) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 0) return false;
        return true;
    }

    public void updateImg(User user, String url) {
        User updateUser = new User();
        updateUser.setAvatarUrl(url);
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdEqualTo(user.getId());
        userMapper.updateByExampleSelective(updateUser, userExample);
    }

    public void updatePassword(User user, String newPassword) {
        User updateUser = new User();
        updateUser.setPassword(newPassword);
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdEqualTo(user.getId());
        userMapper.updateByExampleSelective(updateUser, userExample);
    }
}
