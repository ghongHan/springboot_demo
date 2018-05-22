package com.hskj;

import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by hongHan_gao
 * Date: 2018/5/22
 * 采用递归，将json字符串转换成map
 */


public class Json2Map {

    public static void main(String[] args) {
        String para = "{'status':'OK'," +
                "'result':{" +
                "'location':{" +
                "'lng':120.186281," +
                "'lat':30.193848" +
                "}," +
                "'formatted_address':'浙江省杭州市滨江区建业路'," +
                "'business':'开发区'," +
                "'addressComponent':{" +
                "'city':'杭州市'," +
                "'direction':''," +
                "'distance':''," +
                "'district':'滨江区'," +
                "'province':'浙江省'," +
                "'street':'建业路'," +
                "'street_number':''" +
                "}," +
                "'cityCode':179" +
                "}" +
                "}";
        System.out.println(json2Map(para, new HashMap()));

    }

    /**
     * json字符串转map集合
     * @param jsonStr json字符串
     * @param map     接收的map
     * @return
     */
    public static Map<String, Object> json2Map(String jsonStr, Map<String, Object> map) {
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        map = JSONObject.fromObject(jsonObject);
        Map<String, Object> resultMap = new HashMap<>();
        //递归map的value,如果
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if(entry.getValue() instanceof Map){
                resultMap = json2map1(entry, map, new HashMap<String, Object>());
            }else{
                resultMap.put(entry.getKey(), entry.getValue());
            }
        }
        return resultMap;
    }

    /**
     * json转map,递归调用的方法
     * @param map
     * @return
     */
    public static Map<String, Object> json2map1(Map.Entry<String, Object> entry, Map<String, Object> map, Map<String, Object> resultMap) {
        if (entry.getValue() instanceof Map) {
            JSONObject jsonObject = JSONObject.fromObject(entry.getValue());
            Map<String, Object> map1 = JSONObject.fromObject(jsonObject);
            for (Map.Entry<String, Object> entry1 : map1.entrySet()) {
                if (entry1.getValue() instanceof Map) {
                    map1 = json2map1(entry1, map1, resultMap);
                } else {
                    if(StringUtils.isEmpty(entry1.getValue())){
                        continue;
                    }
                    resultMap.put(entry1.getKey(), entry1.getValue());
                }
            }
        } else {
            resultMap.put(entry.getKey(), entry.getValue());
        }
        return resultMap;
    }


}
