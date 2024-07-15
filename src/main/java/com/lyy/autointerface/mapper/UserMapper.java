package com.lyy.autointerface.mapper;

import com.lyy.autointerface.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from User where id = #{id}")
    User selectById(int id);
    User selectByName(String name);
}
