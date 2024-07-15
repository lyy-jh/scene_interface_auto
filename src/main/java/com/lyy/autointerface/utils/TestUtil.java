package com.lyy.autointerface.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;


import com.alibaba.fastjson.JSON;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;


public class TestUtil {
    /**
     * 使用JsonPath获取json路径，并获取返回的token
     * @param httpResponse
     * @param jsonPath
     * @return
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws IOException
     */
    public static Map<String,String> getToken(CloseableHttpResponse httpResponse,String jsonPath) throws ParseException, IOException{
        Map<String,String> responseTokenMap = new HashMap<>();
        //响应结果字符串
        String responseString = EntityUtils.toString(httpResponse.getEntity(),"utf-8");

        ReadContext ctx = JsonPath.parse(responseString);
        String token = ctx.read(jsonPath);
        if(null==token||"".equals(token)) {
            new Exception("token不存在");
        }

        responseTokenMap.put("x-ba-token", token);
        return responseTokenMap;

    }
    /**
     * 遍历excel,sheet参数
     * @param filePath
     * @param sheetId
     * @return
     * @throws IOException
     */
    public static Object[][] dtt(String filePath,int sheetId) throws IOException{
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sh = wb.getSheetAt(sheetId);
        int numberRow = sh.getPhysicalNumberOfRows();
        int numberCell = sh.getRow(0).getLastCellNum();

        Object[][] dttData = new Object[numberRow][numberCell];
        for(int i=0;i<numberRow;i++) {
            if(null==sh.getRow(i)||"".equals(sh.getRow(i))) {
                continue;
            }
            for(int j=0;j<numberCell;j++) {
                if(null==sh.getRow(i).getCell(j)||"".equals(sh.getRow(i).getCell(j))) {
                    continue;
                }
                XSSFCell cell = sh.getRow(i).getCell(j);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                dttData[i][j]  = cell.getStringCellValue();
            }
        }
        return dttData;
    }
}

