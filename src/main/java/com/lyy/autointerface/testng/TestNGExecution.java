package com.lyy.autointerface.testng;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/run")
@Slf4j
public class TestNGExecution {

    @RequestMapping(name="/{taskId}")
    public void TestNgRun(@PathVariable int taskId) {
        TestNG testNG = new TestNG();
        List<XmlSuite> suites = new ArrayList<>();

        XmlSuite suite = new XmlSuite();
        suite.setName("Automated Test Suite");

        XmlTest test = new XmlTest(suite);
        test.setName("Automated Test");

        // 指定你的测试类
        XmlClass xmlClass = new XmlClass("com.lyy.autointerface.test.TestCase");
        test.getClasses().add(xmlClass);

        log.info("testng被调用了");
        suites.add(suite);
        testNG.setXmlSuites(suites);
        testNG.run();
    }
}
