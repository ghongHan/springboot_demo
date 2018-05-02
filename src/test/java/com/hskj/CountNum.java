package com.hskj;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongHan_gao
 * Date: 2018/3/9
 * 运用hashMap统计数组中各个数据出现的次数
 */


public class CountNum {

    public static void main(String[] args){

        String[] params = {"liHao", "zhangSan", "Tom", "liHao", "zhangSan","zhangSan", "Lucy"};
        Map<String, Integer> countMap = new HashMap<>();

        for(int i = 0; i < params.length; i++){
            if(null == countMap.get(params[i])){
                countMap.put(params[i], 1);
            }else{
                countMap.put(params[i], countMap.get(params[i]) + 1);
            }
        }

        for(String key : countMap.keySet()){
            System.out.println(key + "=" + countMap.get(key));
        }

    }

}
