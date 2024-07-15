package com.lyy.autointerface.controller;

import com.lyy.autointerface.entity.BasicInterfaceInfo;
import com.lyy.autointerface.entity.CaseDataInfo;
import com.lyy.autointerface.entity.ResultBean;
import com.lyy.autointerface.entity.Task;
import com.lyy.autointerface.service.BasicInterfaceInfoService;
import com.lyy.autointerface.service.CaseDataInfoService;
import com.lyy.autointerface.service.TaskService;
import com.lyy.autointerface.test.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    BasicInterfaceInfoService basicInterfaceInfoService;

    @Autowired
    CaseDataInfoService caseDataInfoService;

    /**
     * 查询任务的全部信息
     * @param taskId
     * @return
     */
    @GetMapping("/{taskId}")
    @ResponseBody
    public ResultBean<?> selTask(@PathVariable int taskId){
        Task task = taskService.selById(taskId);
        log.info("查询出任务信息为："+task.toString());
       List<BasicInterfaceInfo> list = basicInterfaceInfoService.selByTaskId(taskId);
        task.setBasicInterfaceInfoList(list);
        for(BasicInterfaceInfo b:list){
            List<CaseDataInfo> caseInfoList = caseDataInfoService.selByInterfaceId(b.getInterfaceId(),taskId);
            b.setCaseDataInfoList(caseInfoList);
        }
        return new ResultBean<>(task);
    }


    @GetMapping("/run/{taskId}")
    @ResponseBody
    public ResultBean<?> execute(@PathVariable int taskId) throws IOException {
        //查询出所有的接口
        List<BasicInterfaceInfo> basicInterfaceInfoList = basicInterfaceInfoService.selByTaskId(taskId);
        log.info("查询出的接口数量"+basicInterfaceInfoList.size());
        //解析接口数据
        for(BasicInterfaceInfo b: basicInterfaceInfoList){
            String url = b.getInterfaceUrl();
            String requestType = b.getInterfaceRequestType();
            //查询用例
            List<CaseDataInfo> caseDataInfoList = caseDataInfoService.selByInterfaceId(b.getInterfaceId(),taskId);
            log.info("查询出的用例数量"+basicInterfaceInfoList.size());
//            CaseData caseData= new CaseData();
//            caseData.data(caseDataInfoList);
            String params="";
            String header = "";
            String assertData = "";
            if(caseDataInfoList.size()>0){
                params = caseDataInfoList.get(0).getCaseRequestParam();
                header = caseDataInfoList.get(0).getCaseRequestHeader();
                assertData = caseDataInfoList.get(0).getCaseAssert();
            }
            log.info("从数据库查询到的参数"+params);
            if(requestType.equalsIgnoreCase("post")){

                Request.postApiTest(url,params,header,assertData);
            }else if(requestType.equalsIgnoreCase("put")){
                Request.putApiTest(url,params,header,assertData);
            }
        }
        return new ResultBean<>();
    }
}
