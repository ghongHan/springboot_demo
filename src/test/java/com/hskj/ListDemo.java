package com.hskj;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongHan_gao
 * Date: 2018/7/19
 * subList() : 截取list集合
 */


public class ListDemo {

    @Test
    public void subListTest(){

        List<String> cards = new ArrayList<>();
        cards.add("小王");
        cards.add("黑桃2");
        cards.add("红桃2");
        cards.add("方块2");
        cards.add("梅花2");
        cards.add("大王");
        System.out.println("cards：" + cards);
        //截取黑桃2、红桃2、方块2、梅花2
        List<String> bomb = cards.subList(1, 5);
        System.out.println("bomb: " + bomb);
        //当修改bomb里的元素时，也会修改cards集合里的元素
        //清空bomb里的元素
        bomb.clear();
        System.out.println("bomb: " + bomb);
        System.out.println("cards：" + cards);
    }
}
