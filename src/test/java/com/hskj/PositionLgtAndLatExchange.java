package com.hskj;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hongHan_gao
 * Date: 2018/5/19
 * 百度地图的地址与经纬度互换
 */


public class PositionLgtAndLatExchange {

    public static void main(String[] args) throws Exception {
        String result = getLocation("30.193848", "120.186281");
        JSONObject jsonObjectResult = JSONObject.parseObject(JSONObject.parseObject(result).getString("result"));
        JSONObject jsonObjectAddressComponent = JSONObject.parseObject(jsonObjectResult.getString("addressComponent"));
        JSONObject jsonObjectLocation = JSONObject.parseObject(jsonObjectResult.getString("location"));
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("city", jsonObjectAddressComponent.getString("city"));
        resultMap.put("district", jsonObjectAddressComponent.getString("district"));
        resultMap.put("province", jsonObjectAddressComponent.getString("province"));
        resultMap.put("street", jsonObjectAddressComponent.getString("street"));
        resultMap.put("business", jsonObjectResult.getString("business"));
        resultMap.put("cityCode", jsonObjectResult.getString("cityCode"));
        resultMap.put("formatted_address", jsonObjectResult.getString("formatted_address"));
        resultMap.put("latitude", jsonObjectLocation.getString("lat"));
        resultMap.put("longitude", jsonObjectLocation.getString("lng"));
        System.out.println(result);

    }

    public static String getLocation(String latitude, String longitude) throws Exception {
        BufferedReader in = null;
        URL url = new URL("http://api.map.baidu.com/geocoder?location="+ latitude+","+longitude+"&output=json&key="+"gzf2e7XF05t7Afa4TbbCeBh3Cay09OOV");
        in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        String result;
        StringBuilder stringBuilder = new StringBuilder();
        while((result = in.readLine()) != null){
            stringBuilder.append(result.trim());
        }
        return stringBuilder.toString();
    }

}
