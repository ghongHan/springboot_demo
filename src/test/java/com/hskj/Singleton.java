package com.hskj;

/**
 * 单例模式----双重校验
 * Created by hongHan_gao
 * Date: 2018/1/16
 */


public class Singleton {

    private static Singleton instance = null;

    private Singleton(){}

    public static Singleton getInstance(){
        //第二次检查，检查instance是否已被初始化，如果其他线程获取过锁，即instance已被初始化，则直接返回instance
        if(null == instance){
            synchronized(Singleton.class){
                //第一次检查，当多个线程进入getInstance方法时，若instance为null，多个线程竞争同步锁，第一个抢到锁的线程，创建instance对象，后面的线程等待
                if(null == instance){
                    instance = new Singleton();
                    System.out.println(Thread.currentThread().getName() + "\t" + "instance is initalized....");
                }else{
                    System.out.println(Thread.currentThread().getName() + "\t" + "instance is initalized....");
                }
            }
        }
        return instance;
    }
}
