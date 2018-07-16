package com.hskj;

import java.util.Arrays;

/**
 * Created by hongHan_gao
 * Date: 2018/7/9
 * 数组的扩容
 */


public class ArrayExpand {

    public static void main(String[] args){
        int[] param = {1, 2, 3};
        param = Arrays.copyOf(param, param.length + 1);
        param[param.length - 1] = 4;
        System.out.println(Arrays.toString(param));
        String s;
    }

}
