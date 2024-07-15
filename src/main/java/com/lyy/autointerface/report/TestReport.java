package com.lyy.autointerface.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ResourceCDN;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.TestAttribute;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.*;

public class TestReport implements IReporter {
    static Date date = new Date();
    //String类的format()方法用于创建格式化的字符串以及连接多个字符串对象,%tx代表日期与时间类型。
    // %tF “年-月-日”格式
    static String day = String.format("%tF", date);

    // %tT “HH:MM:SS”格式（24时制）,报告名称不能使用此格式
    //static String time = String.format("%tT", date);


    static String hour = String.format("%tH", date);
    static String minute = String.format("%tM", date);
    static String second = String.format("%tS", date);

    //生成的路径以及文件名
    private static final String OUTPUT_FOLDER="test-output/";
    private static final String FILE_NAME="report"+day+" "+hour+minute+second+".html";

    private ExtentReports extent;


    //Generate a report for the given suites into the specified output directory.

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        // 初始化报告的格式
        init();

        boolean createSuiteNode=false;
        //如果套件个数大于1，创建套件结点
        if(suites.size()>1) {
            createSuiteNode=true;
        }

        // 遍历suites
        for(ISuite suite: suites) {
            //suite.getResults()返回一个Map类型的集合
            Map<String, ISuiteResult> result = suite.getResults();

            //如果suite里没有任何用例，直接跳过，不在报告里生成
            if(result.size()==0) {
                continue;
            }


            //统计suite下成功、失败、跳过的总用例数
            int suiteFailSize=0;
            int suitePassSize=0;
            int suiteSkipSize=0;
            //suite级别的结点
            ExtentTest suiteTest=null;

            //存在多个suite的情况下，在报告中将同一个suite的测试结果归为一类，创建一级节点
            if(createSuiteNode) {
                suiteTest = extent.createTest(suite.getName()).assignCategory(suite.getName());
            }

            boolean createSuiteResultNode=false;
            // 如果suite中有超过一个test标签
            if(result.size()>1) {
                createSuiteResultNode=true;
            }

            for(ISuiteResult r: result.values()) {
                // test级别的结点
                ExtentTest resultNode;
                ITestContext context = r.getTestContext();
                if(createSuiteResultNode) {
                    //没有创建suite的情况下，将SuiteResult创建为一级节点，否则创建为suite的一个子节点
                    if(suiteTest==null) {
                        resultNode = extent.createTest(r.getTestContext().getName());
                    }else {
                        resultNode = suiteTest.createNode(r.getTestContext().getName());
                    }
                    //suite中test标签个数不超过一个时，只需要suit级别一个结点，不需要test级别的结点。
                }else {
                    resultNode= suiteTest;
                }

                //如果有test级别的结点，即suite中的test标签超过1个
                if(resultNode!=null) {
                    //设置test级别结点的名称，suite的名称+用例名称
                    resultNode.getModel().setName(suite.getName()+" : "+r.getTestContext().getName());
                    if(resultNode.getModel().hasCategory()) {
                        resultNode.assignCategory(r.getTestContext().getName());
                    }else {
                        resultNode.assignCategory(suite.getName(),r.getTestContext().getName());
                    }
                    //设置test级别中用例开始时间和结束时间
                    resultNode.getModel().setStartTime(r.getTestContext().getStartDate());
                    resultNode.getModel().setEndTime(r.getTestContext().getEndDate());
                    //统计suiteResult下的数据，即test标签下用例运行结果
                    int passSize = r.getTestContext().getPassedTests().size();
                    int failSize = r.getTestContext().getFailedTests().size();
                    int skipSize = r.getTestContext().getSkippedTests().size();
                    // suite中用例执行结果包括所有test标签中用例执行结果。
                    suitePassSize +=passSize;
                    suiteFailSize +=failSize;
                    suiteSkipSize +=skipSize;
                    if(failSize>0) {
                        resultNode.getModel().setStatus(Status.FAIL);
                    }

                    resultNode.getModel().setDescription(String.format("Pass: %s ; Fail: %s ; Skip: %s ;",passSize,failSize,skipSize));
                }


                buildTestNodes(resultNode,context.getFailedTests(),Status.FAIL);
                buildTestNodes(resultNode,context.getPassedTests(),Status.PASS);
                buildTestNodes(resultNode,context.getSkippedTests(),Status.SKIP);

            }

            //如果存在超过一个suite的情况
            if(suiteTest!=null) {
                suiteTest.getModel().setDescription(String.format("Pass: %s; Fail: %s; Skip: %s;", suitePassSize,suiteFailSize,suiteSkipSize));
                if(suiteFailSize>0) {
                    suiteTest.getModel().setStatus(Status.FAIL);
                }
            }

        }

        extent.flush();
    }

    /**
     * 对生成的测试报告格式进行初始化
     */
    public void init() {
        //文件不存在的话进行创建
        File reportDir = new File(OUTPUT_FOLDER);
        if(!reportDir.exists()&&!reportDir.isDirectory()) {
            reportDir.mkdir();
        }

        // 创建一个html文件
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER+FILE_NAME);

        //设置静态文件的DNS
        //怎么样解决cdn.rawgit.com访问不了的情况(也就是CSS无法访问的情况)
        htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);

        htmlReporter.config().setDocumentTitle("api自动化测试报告");
        htmlReporter.config().setReportName("api自动化测试报告");
        //true表示显示图表，false表示隐藏图表
        htmlReporter.config().setChartVisibilityOnOpen(true);
        // 可视化图表显示在最上端
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        // 设置主题
        htmlReporter.config().setTheme(Theme.STANDARD);

        //解决测试报告中文乱码问题
        htmlReporter.config().setEncoding("utf8");

        //设置css样式
        htmlReporter.config().setCSS(".node.level-1  ul{ display:none;} .node.level-1.active ul{display:block;}");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //手动配置
        extent.setReportUsesManualConfiguration(true);
    }


    private void buildTestNodes(ExtentTest extentTest, IResultMap tests, Status status) {
        //存在父节点时获取父节点的标签
        String[] categories = new String[0];
        if(extentTest!=null) {
            List<TestAttribute> categoryList =  extentTest.getModel().getCategoryContext().getAll();
            categories = new String[categoryList.size()];
            for(int index=0;index<categoryList.size();index++) {
                categories[index] =categoryList.get(index).getName();
            }
        }

        ExtentTest test;

        if(tests.size()>0) {
            //调整用例排序，按时间排序
            Set<ITestResult> treeSet = new TreeSet<>(new Comparator<ITestResult>() {

                @Override
                public int compare(ITestResult arg0, ITestResult arg1) {
                    // TODO Auto-generated method stub
                    return arg0.getStartMillis()<arg1.getStartMillis()?-1:1;
                }
            });

            treeSet.addAll(tests.getAllResults());
            for(ITestResult result:treeSet) {
                Object[] parameters = result.getParameters();
                String name="";
                //如果有参数，则使用参数的toString组合代替报告中的name
                for(Object param:parameters) {
                    name+=param.toString();
                }

                if(name.length()>0) {

                    if(name.length()>50) {
                        name=name.substring(0, 49)+"...";
                    }

                }else {
                    name=result.getMethod().getMethodName();
                }

                if(extentTest==null) {
                    test = extent.createTest(name);
                }else {
                    //作为子节点进行创建时，设置同父节点的标签一致，便于报告检索
                    test = extentTest.createNode(name).assignCategory(categories);
                }

                for(String group :result.getMethod().getGroups()) {
                    test.assignCategory(group);
                }

                List<String> outputList = Reporter.getOutput(result);

                for(String output:outputList) {
                    //将用例的log输出报告中
                    test.debug(output);
                }
                if(result.getThrowable()!=null) {
                    test.log(status, result.getThrowable());
                }else {

                    test.log(status, "Test"+status.toString().toLowerCase()+"ed");
                }

                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));

            }
        }

    }


    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

}
