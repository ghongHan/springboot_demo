package com.hskj;

/**
 * Created by hongHan_gao
 * Date: 2018/5/17
 * 冒泡排序的第一种实现, 没有任何优化
 */


public class BubbleSortOne {

    public static void main(String... args){
        int[] params = {1,1,2,0,9,3,12,7,8,3,4,65,22};
        long startTime = System.currentTimeMillis();
        sort(params, params.length);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        for (int param : params){
            System.out.print(param + "\t");
        }
    }

    /**
     * 排序
     * @param params 数组对象
     * @param length 数组长度
     */
    public static void sort(int[] params, int length){
        int i,j;
        for(i = 0; i < length; i++){
            for(j = 1; j < length - i; j++){
                if(params[j - 1] > params[j]){
                    int temp = params[j - 1];
                    params[j - 1] = params[j];
                    params[j] = temp;
                }
            }
        }
    }

    public static void bubbleSort2(int [] a, int n){
        int j, k = n;
        boolean flag = true;//发生了交换就为true, 没发生就为false，第一次判断时必须标志位true。
        while (flag){
            flag=false;//每次开始排序前，都设置flag为未排序过
            for(j=1; j<k; j++){
                if(a[j-1] > a[j]){//前面的数字大于后面的数字就交换
                    //交换a[j-1]和a[j]
                    int temp;
                    temp = a[j-1];
                    a[j-1] = a[j];
                    a[j]=temp;

                    //表示交换过数据;
                    flag = true;
                }
            }
            k--;//减小一次排序的尾边界
        }//end while
    }//end

}
