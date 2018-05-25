package com.hskj;

import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by hongHan_gao
 * Date: 2018/5/25
 * stringTokenizer分割数据
 */


public class TestStringTokenizer {


    /**
     * 构造目标字符串
     * @param length
     * @return
     */
    private static String getOriginStr(int length){
        StringBuilder sb = new StringBuilder();
        StringBuilder resultBuilder = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i<length; i++){
            sb.append(random.nextInt(9))
                    .append(random.nextInt(9))
                    .append(random.nextInt(9));

            resultBuilder.append(sb);
            sb.delete(0, sb.length());
            if(i != length - 1){
                resultBuilder.append(".");
            }
        }
        return resultBuilder.toString();
    }

    public static void main(String [] args){
        String originStr = getOriginStr(10);
        System.out.println("originStr:" + originStr);
        Long startTime = System.nanoTime();
        StringTokenizer stringTokenizer = new StringTokenizer(originStr, ".");
        System.out.println("nanoTime------" + startTime);
        while (stringTokenizer.hasMoreElements()) {
            System.out.println("stringTokenizer========>" + stringTokenizer.nextToken());

        }

    }
}
