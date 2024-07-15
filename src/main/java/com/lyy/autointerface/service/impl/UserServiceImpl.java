package com.lyy.autointerface.service.impl;

import com.lyy.autointerface.mapper.UserMapper;
import com.lyy.autointerface.entity.User;
import com.lyy.autointerface.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectById(int id) {
        User u = userMapper.selectById(id);
        log.info("impl"+u.toString());
        return u;
    }

    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }
}
