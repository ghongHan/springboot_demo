package com.hskj;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Random;

/**
 * Created by hongHan_gao
 * Date: 2018/7/3
 */


public class StartsWithAndEndWithDemo {

    public static void main(String[] args){
        String str = "Tom";
        String param = "a";
        System.out.println("格式化字符串：" + String.format("%05d", 99));
        System.out.println("检查形参参数是否与你要检查的字符串开头相同:" + str.startsWith("t"));
        System.out.println("字符串是否以指定后缀str结束" + str.endsWith("om"));
        System.out.println("获取随机数：" + new Random().nextInt(9));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("jhasdnmf");
        stringBuilder.deleteCharAt(0);
        System.out.println(stringBuilder.toString());
        System.out.println(ObjectUtils.firstNonNull(null,str, param));
        System.out.println("将字符串转化为数组：" + str.toCharArray());
    }

}
