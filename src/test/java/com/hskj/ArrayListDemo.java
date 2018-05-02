package com.hskj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongHan_gao
 * Date: 2018/3/8
 */


public class ArrayListDemo {

    public static void main(String[] args){
        List<String> arrayList = new ArrayList<>();
        arrayList.add("hongHan_gao");
        arrayList.add("苏武");
        arrayList.add("Tom");
        arrayList.add("dongZhi");

        for(String aList : arrayList){
            System.out.println(aList);
        }
        System.out.println("*******指定位置添加元素*******");
        arrayList.add(1, "苏武牧羊");
        for(String newAList : arrayList){
            System.out.println(newAList);
        }
        System.out.println("**********指定位置修改元素************");
        arrayList.set(1, "一本书");
        for(String dNewAList : arrayList){
            System.out.println(dNewAList);
        }
    }

}
