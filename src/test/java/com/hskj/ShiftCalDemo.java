package com.hskj;

import org.junit.Test;

/**
 * Created by hongHan_gao
 * Date: 2018/7/19
 * 位运算示例:
 *  左运算（<<）: num << n 即 num * 2 的n次幂；
 *  右运算(>>) : num >> n 即 num / 2 的n次幂；
 */


public class ShiftCalDemo {

    @Test
    public void ShiftCalTest(){
        int num = 16;
        System.out.println(num << 3);
        System.out.println(num >> 3);
    }
}
