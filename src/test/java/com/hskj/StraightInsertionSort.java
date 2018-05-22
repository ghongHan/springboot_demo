package com.hskj;

/**
 * Created by hongHan_gao
 * Date: 2018/5/17
 * 直接插入排序：将一个记录插入到已排序好的有序表中，从而得到一个新，
 * 记录数增1的有序表。即：先将序列的第1个记录看成是一个有序的子序列，
 * 然后从第2个记录逐个进行插入，直至整个序列有序为止。
 */


public class StraightInsertionSort {

    public static void main(String[] args){
        int[] param = {3,1,5,7,2,4,9,6,10,8};
        StraightInsertionSort obj = new StraightInsertionSort();
        System.out.print("初始值：");
        obj.print(param);
        obj.insertSort(param);
        System.out.print("\n排序后：");
        obj.print(param);

    }

    public void print(int[] param){
        for(int i=0; i<param.length; i++){
            System.out.print(param[i] + " ");
        }
    }

    public void insertSort(int[] param){
        //从头部第一个当作已排好序的，把后面的一个一个的插到已经排好的列表中
        for(int i = 1; i<param.length; i++){
            int j;
            //x为待待插入的元素
            int x = param[i];
            //通过循环，逐个后移一位找到要插入的位置。
            for (j = i; j>0 && x<param[j-1]; j--){
                param[j] = param[j-1];
            }
            param[j] = x;
        }
    }

}
