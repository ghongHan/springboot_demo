package com.hskj;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hongHan_gao
 * Date: 2018/7/6
 * 求集合的交集、并集、差集
 */


public class SetDemo {

    public static void main(String[] args){

        Set<Integer> setA = new HashSet<>();
        setA.add(2);
        setA.add(4);
        setA.add(6);
        setA.add(8);
        setA.add(9);
        Set<Integer> setB = new HashSet<>();
        setB.add(1);
        setB.add(3);
        setB.add(5);
        setB.add(7);
        setB.add(9);

        //求交集
        // setA.retainAll(setB);
        // System.out.println("A、B交集为：" + setA);
        //求并集
        // setA.addAll(setB);
        // System.out.println("A、B并集为：" + setA);
        setA.removeAll(setB);
        System.out.println("A、B差集为：" + setA);
      }

}
