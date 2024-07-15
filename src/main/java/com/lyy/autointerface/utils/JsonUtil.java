package com.lyy.autointerface.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * json解析的工具方法类
 * @author liyingying
 *  主要查询两种json对象的的值
 *  (1)第一种最简单的,这个json对象在整个json串的第一层，例如上面截图中的per_page，
 *  	这个per_page就是通过Jpath这个参数传入，返回的结果就是其对应的值.
 *  (2)第二种是查询数组中某个json对象的值，我想查询data数组下第一个用户信息里面的first_name的值，
 *  	这个时候Jpath的写法就是data[0]/first_name，查询结果应该是first_name对应的值
 *
 */
public class JsonUtil {


    /**
     *
     * @param responseJson  该变量为拿到响应字符串通过JSON转换为json对象
     * @param Jpath  Jpath表示用户想要查询json对象的值的路径写法
     * 		Jpath写法举例：
     * 				(1) per_page 是一个json对象
     * 				(2) data[1]/first_name  data是一个json数组，[1]表示索引，/first_name表示data数组下某一个元素下的json对象的名称为first_name
     *
     * @return 返回first_name这个json对象名称对应的值
     */
    public static String getValueByJpath(JSONObject responseJson,String Jpath) {

        //如果Jpath为空的话，返回整个responseJson对象
        Object obj = responseJson;

        //1.如果是解析类似per_page的json对象，直接返回对应的值,执行第一个if体，注意：per_page通过for循环后为Jpath.split("/")为[per_page]
        //2.如果是需要解析类似data数组中json对象的值，例如data[1]/first_name,Jpath.split("/")为[data[1],first_name]
        //先执行第二个if语句得到data.get(1),在执行第一个if语句得到data.get(1).get(first_name)
        for(String s:Jpath.split("/")) {
            if(!s.isEmpty()) {
                if(!(s.contains("[")||s.contains("]"))) {
                    obj = ((JSONObject)obj).get(s);

                }else if(s.contains("[")||s.contains("]")) {
                    //该语句中\\为转义符
                    obj= ((JSONArray)((JSONObject)obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replaceAll("]", "")));

                }
            }
        }

        return obj.toString();

    }
}
