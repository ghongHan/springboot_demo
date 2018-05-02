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

        Collections.sort(params, new Comparator<Boy>() {
            @Override
            public int compare(Boy o1, Boy o2) {
                if((o1.getAge() - o2.getAge()) > 0){
                    return 1;
                }else if((o1.getAge() - o2.getAge()) < 0){
                    return -1;
                }else{
                    return o1.getName().compareTo(o2.getName());
                }
            }
        });

        for (Boy boy : params){
            System.out.println(boy);
        }
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