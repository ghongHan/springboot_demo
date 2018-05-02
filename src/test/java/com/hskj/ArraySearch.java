package com.hskj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by hongHan_gao
 * Date: 2018/3/6
 * 二分法查询数组
 */


public class ArraySearch {

    public static void main(String[] params){
        int[] nums = {2, 17, 9, 20, 34, 4, 100, 19};
        Arrays.sort(nums);
        //保存查找的数据的下标
        int index= -1;
        //数组的起始下标
        int start = 0;
        //数组的终止下标
        int end = nums.length - 1;
        //数组的中间数据的下标
        int middle;

        Scanner input = new Scanner(System.in);
        System.out.println("Please input:");
        Integer number = input.nextInt();
        outer:
        while(start <= end){
            middle = (start + end)/2;
            switch(number.compareTo(nums[middle])){
                case 0:
                    index = middle + 1;
                    System.out.println("find at --" + index);
                    break outer;
                case 1:
                    start = middle + 1;
                    break;
                case -1:
                    end = middle - 1;
                    break;
            }
        }
        if(index == -1){
            System.out.println("no find!");
        }
    }

}
