package com.lyy.autointerface.service;

import com.lyy.autointerface.entity.User;

public interface UserService {
    User selectById(int id);
    User selectByName(String name);
}
