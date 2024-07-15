//package com.lyy.autointerface.test;
//
//import com.lyy.autointerface.entity.BasicInterfaceInfo;
//import com.lyy.autointerface.entity.CaseDataInfo;
//import com.lyy.autointerface.service.BasicInterfaceInfoService;
//import com.lyy.autointerface.service.CaseDataInfoService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//import java.util.List;
//
//@Slf4j
//public class TestCase {
//
//    @Autowired
//    BasicInterfaceInfoService basicInterfaceInfoService;
//
//    @Autowired
//    CaseDataInfoService caseDataInfoService;
//
//   @Test
//    public void test() throws IOException {
//
//       int taskId =3;
//       //查询出所有的接口
//       List<BasicInterfaceInfo> basicInterfaceInfoList = basicInterfaceInfoService.selByTaskId(taskId);
//       //解析接口数据
//       for(BasicInterfaceInfo b: basicInterfaceInfoList){
//           String url = b.getInterfaceUrl();
//           String requestType = b.getInterfaceRequestType();
//           //查询用例
//           List<CaseDataInfo> caseDataInfoList = caseDataInfoService.selByInterfaceId(b.getInterfaceId(),taskId);
//           String params="";
//           String header = "";
//           if(caseDataInfoList.size()>0){
//              params = caseDataInfoList.get(0).getCaseRequestParam();
//              header = caseDataInfoList.get(0).getCaseRequestHeader();
//           }
//           if(requestType.equalsIgnoreCase("post")){
//
//                PostRequest.postApiTest(url,params,header);
//           }
//       }
//       log.info("TEST执行了");
//    }
//}
