package com.hskj.threadDemo;

/**
 * Created by hongHan_gao
 * Date: 2018/5/28
 */


public class Actor extends Thread {

    @Override
    public void run() {
        System.out.println(getName() + "程咬金扮演者！");
        int showCount = 0;
        boolean keepRunning = true;
        while(keepRunning){
            System.out.println(getName() + "登台演出" + (++showCount));
            //showCount为10的整数倍，线程就休眠5秒
            if(showCount % 10 == 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(100 == showCount){
                keepRunning = false;
                System.out.println(getName() + "演出结束！");
            }
        }

    }

    public static void main(String[] args) {
        Thread actor = new Actor();
        actor.setName("Mr. Chen");
        actor.start();
        Thread actress = new Thread(new Actress(), "Miss. Lucy");
        actress.start();
    }
}
class Actress implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "程咬金老婆扮演者！");
        int showCount = 0;
        boolean keepRunning  = true;
        while(keepRunning){
            System.out.println(Thread.currentThread().getName() + "登台演出" + (++showCount));
            if(showCount % 10 == 0){
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(100 == showCount){
                keepRunning = false;
                System.out.println(Thread.currentThread().getName() + "结束演出!");
            }
        }
    }
}