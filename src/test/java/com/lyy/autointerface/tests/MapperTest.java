//package com.lyy.autointerface.tests;
//
//import com.lyy.autointerface.entity.Task;
//import com.lyy.autointerface.entity.User;
//import com.lyy.autointerface.mapper.TaskMapper;
//import com.lyy.autointerface.service.TaskService;
//import com.lyy.autointerface.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Component;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//
//@Component
//@Slf4j
//@SpringBootTest
//public class MapperTest {
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    TaskService taskService;
//
//    @Autowired
//    TaskMapper taskMapper;
//
//    @Test(dataProvider = "data")
//    public User selectById(int id){
//        User u = userService.selectById(id);
//        log.info("查询出的用户为："+u.toString());
//        return u;
//    }
//
//    @DataProvider(name = "data")
//    public Object[][] data(){
//        Object[][] data = new Object[][]{
//                {1}
//        };
//        return data;
//    }
//
//    @Test
//    public void test(){
//        Task task = taskMapper.selById(3);
//        Task task2    = taskService.selById(3);
//        System.out.println(task);
//    }
//
//}
