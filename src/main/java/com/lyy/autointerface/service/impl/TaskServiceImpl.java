package com.lyy.autointerface.service.impl;

import com.lyy.autointerface.entity.Task;
import com.lyy.autointerface.mapper.TaskMapper;
import com.lyy.autointerface.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Override
    public Task selById(int taskId) {
        Task task = taskMapper.selById(taskId);
        String taskName = task.getTaskName();
        log.info("taskName"+taskName);
        log.info("impl"+task.toString());
        return task;
    }
}
