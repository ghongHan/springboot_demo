package com.hskj;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hongHan_gao
 * Date: 2018/3/8
 */


public class HashMapDemo {

    public static void main(String[] args){
        HashMap<Integer, String> data = new HashMap<>();
        data.put(1, "Tom");
        data.put(2, "Marry");
        data.put(3, "Lucy");
        data.put(4, "Jack");
        data.put(5, "Jame");

        System.out.println(data.size());

        Set<Integer> keys = data.keySet();
        for(Integer key : keys){
            String value = data.get(key);
            System.out.println(key + "=" + value);
        }

        Set<Map.Entry<Integer, String>> entries = data.entrySet();
        for(Map.Entry<Integer, String> entry : entries){
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

    }

}
