package com.lyy.autointerface.mapper;

import com.lyy.autointerface.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TaskMapper {
    @Select("select * from task where task_id = #{taskId}")
    Task selById(int taskId);
}
