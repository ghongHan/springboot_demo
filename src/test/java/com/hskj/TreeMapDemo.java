package com.hskj;

import lombok.Data;

import java.util.TreeMap;

/**
 * Created by hongHan_gao
 * Date: 2018/3/9
 */


public class TreeMapDemo {

    public static void main(String[] args){
        TreeMap<Object, String> data = new TreeMap<>();
        data.put(new Person("Tom", 20), "汤姆");
        data.put(new Person("Jack", 23), "杰克");
        data.put(new Person("Jerry", 19), "杰瑞");
        data.put(new Person("Lucy", 21), "露西");
        System.out.println(data);
    }
}
@Data
class Person implements Comparable<Person>{

    private String name;

    private Integer age;

    public Person(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        if((this.age - o.age) >0 ){
            return 1;
        }else if((this.age - o.age) < 0){
            return -1;
        }
        return 0;
    }
}
