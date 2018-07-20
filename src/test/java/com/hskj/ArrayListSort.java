package com.hskj;

import lombok.Data;
import org.hibernate.secure.spi.IntegrationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hongHan_gao
 * Date: 2018/3/9
 * 运用Collections.sort()及指定的比较规则，对集合进行排序
 */


public class ArrayListSort {

    public static void main(String[] args){
        List<Boy> params = new ArrayList<>();
        params.add(new Boy("Tom", 20, 10));
        params.add(new Boy("Jack", 22, 1));
        params.add(new Boy("Jame", 18, 19));
        params.add(new Boy("wangWu", 23, 9));
        params.add(new Boy("liHao", 20, 8));

        //1.按年龄的大小，升序排列
        Comparator<Boy> sortByAge = new Comparator<Boy>() {
            @Override
            public int compare(Boy o1, Boy o2) {
                return o1.getAge() > o2.getAge()? 1:
                        o1.getAge() < o2.getAge()? -1: 0;
            }
        };
        Collections.sort(params, sortByAge);

        //2.按id的大小，降序排列
        Comparator<Boy> descSortById = new Comparator<Boy>() {
            @Override
            public int compare(Boy o1, Boy o2) {
                return -(o1.getId() > o2.getId()? 1:
                        o1.getId() < o2.getId()? -1: 0);
            }
        };
        Collections.sort(params, descSortById);

        //3.按年龄升序，按id降序-即先按年龄升序排列，相同的年龄再按id降序
        Comparator<Boy> sortByAgeAndId = new Comparator<Boy>() {
            @Override
            public int compare(Boy o1, Boy o2) {
                if(o1.getAge() != o2.getAge()){
                    return o1.getAge() > o2.getAge()? 1: -1;
                }else{
                    return o1.getId() > o2.getId()? 1:
                            o2.getId() < o2.getId()? -1: 0;
                }
            }
        };
        Collections.sort(params, sortByAgeAndId);
        System.out.println(params);
    }
}

@Data
class Boy{

    private String name;

    private Integer age;

    private Integer id;

    public Boy(String name, Integer age, Integer id){
        this.name = name;
        this.age= age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}