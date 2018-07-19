package com.hskj;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by hongHan_gao
 * Date: 2018/7/18
 * 测试Collection中与all相关的方法
 */


public class CollectionAllDemo {

    @Test
    public void collectionAllTest(){
        Collection<String> c1 = new ArrayList<>();
        c1.add("黑桃2");
        c1.add("方块9");
        c1.add("梅花5");
        c1.add("小王");
        Collection<String> c2 = new ArrayList<>();
        c2.add("红桃3");
        c2.add("梅花3");
        c2.add("黑桃8");
        c2.add("大王");

        //c1、c2合并元素
        c2.addAll(c1);  //将c1追加在c2后面
        System.out.println(c2);
        Collection<String> c3 = new ArrayList<>();
        c3.add("大王");
        c3.add("小王");
        //判断合并后的c2中是否含有c3的所有元素
        System.out.println(c2.containsAll(c3)? "含有" : "不包含");
        System.out.println(c2);
    }
}
