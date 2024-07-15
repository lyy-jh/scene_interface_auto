package com.lyy.autointerface.controller;

import com.lyy.autointerface.entity.BasicInterfaceInfo;
import com.lyy.autointerface.entity.CaseDataInfo;
import com.lyy.autointerface.entity.Task;
import com.lyy.autointerface.entity.User;
import com.lyy.autointerface.mapper.TaskMapper;
import com.lyy.autointerface.service.BasicInterfaceInfoService;
import com.lyy.autointerface.service.CaseDataInfoService;
import com.lyy.autointerface.service.TaskService;
import com.lyy.autointerface.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
//@Component
@Slf4j
//@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    BasicInterfaceInfoService basicInterfaceInfoService;

    @Autowired
    CaseDataInfoService caseDataInfoService;


    @GetMapping("/user/{id}")
    public User selectById(@PathVariable int id){
        User u = userService.selectById(id);
        log.info("查询出的用户为："+u.toString());
        return u;
    }

//    @GetMapping("/task/{id}")
//    public Task selTask(@PathVariable int id){
//        Task task = taskService.selById(id);
//        log.info("查询出任务信息为："+task.toString());
//        return task;
//    }


//    @GetMapping("/interface/{id}")
//    public List<BasicInterfaceInfo> selByTask(@PathVariable int id){
//        List<BasicInterfaceInfo> list= basicInterfaceInfoService.selByTaskId(id);
//        for(BasicInterfaceInfo b:list){
//           log.info("查询出接口信息为："+b.toString());
//        }
//        return list;
//    }
//
//    @GetMapping("/interface/{id}")
//    public User selectByInterface(@PathVariable int id){
//        User u = userService.selectById(id);
//        log.info("查询出的用户为："+u.toString());
//        return u;
//    }


//    @GetMapping("/case/{interfaceId}/{taskId}")
//    public CaseDataInfo selByTask(@PathVariable int interfaceId, @PathVariable int taskId){
//        CaseDataInfo caseDataInfo = caseDataInfoService.selByInterfaceId(interfaceId,taskId);
//        log.info("查询出的用例为："+caseDataInfo.toString());
//        return caseDataInfo;
//    }

}
