package com.hskj;

/**
 * Created by hongHan_gao
 * Date: 2018/7/14
 * 找出重复的字符，并输出
 */


public class SearchRepeatChar {

    public static void main(String[] args){
        String param = "no zuo no die";
        for (int i = 0; i < param.length(); i++){
            char chr = param.charAt(i);
            if(chr != ' ' && param.indexOf(chr, i + 1) != -1){
                if(param.lastIndexOf(chr, i -1 ) == -1){
                    System.out.println(chr);
                }
            }
        }
    }
}
