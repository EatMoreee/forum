package com.example.demo.controller;

import com.example.demo.dto.ResultDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/ban", method = RequestMethod.POST)
    public Object ban(@RequestBody UserDTO userDTO) {
        int type = userDTO.getType();
        Long id = userDTO.getId();
        userService.changeState(type,id);
        return ResultDTO.okOf();
    }
}
