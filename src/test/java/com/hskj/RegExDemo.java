package com.hskj;

import org.junit.Test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hongHan_gao
 * Date: 2018/7/17
 * 正则表达类
 */


public class RegExDemo {

    /**
     * 测试matcher。matches（）
     * @param args
     */
/*
    public static void main(String... args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名(用户名包含字母、数字、下划线)：");
        String input = scanner.nextLine();
        Pattern p = Pattern.compile("[a-zA-Z]\\w{4,15}");
        Matcher m = p.matcher(input);
        if(m.matches()){
            System.out.println("用户可用！");
        }else{
            System.out.println("输入格式不正确!");
        }
    }
*/

    /**
     * 测试matcher.find()
     * @param args
     */
    public static void main(String... args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入评论:");
        String input = scanner.nextLine();
        Pattern p = Pattern.compile("我去");
        Matcher m = p.matcher(input);
        if(m.find()){
            System.out.println("出现敏感词语！");
            input = input.replaceAll("我去","**");
            System.out.println(input);
        }else{
            System.out.println("评论成功！");
        }
    }
}
