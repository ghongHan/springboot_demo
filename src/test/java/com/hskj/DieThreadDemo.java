package com.hskj;

/**
 * Created by hongHan_gao
 * Date: 2018/3/10
 * 死锁案例
 */


public class DieThreadDemo {
    public static void main(String[] args){
        Example example = new Example();
        Thread t1 = new DieThread1(example);
        t1.start();
        Thread t2 = new DieThread2(example);
        t2.start();
    }

}
class DieThread1 extends Thread{
    private Example example;

    public DieThread1(Example example){
        this.example = example;
    }

    @Override
    public void run() {
        example.method1();
    }
}

class DieThread2 extends Thread{
    private Example example;

    public DieThread2(Example example){
        this.example = example;
    }

    @Override
    public void run() {
        example.method2();
    }
}


class Example{
    //锁对象obj1、obj2
    private Object obj1 = new Object();
    private Object obj2 = new Object();

    public void method1(){
        synchronized (obj1){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2){
                System.out.println("method1");
            }
        }
    }

    public void method2(){
        synchronized (obj2){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj1){
                System.out.println("method2");
            }
        }

    }

}
