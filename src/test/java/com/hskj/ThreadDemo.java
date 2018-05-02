package com.hskj;

/**
 * Created by hongHan_gao
 * Date: 2018/3/10
 * 比较俩个创建线程的区别
 */


public class ThreadDemo {

    public static void main(String[] args) {
/*        Thread t1 = new TicketThread();
        t1.start();
        Thread t2 = new TicketThread();
        t2.start();*/

        Runnable ticketRunnable = new TicketRunnable();
        Thread t1 = new Thread(ticketRunnable);
        t1.start();
        Thread t2 = new Thread(ticketRunnable);
        t2.start();

    }
}
/*
class TicketThread extends Thread {

    private int ticketNum = 5;

    @Override
    public void run() {
        while (true) {
            System.out.println("第" + this.getName() + "线程卖出" + ticketNum--);
            if(ticketNum < 1){
                break;
            }
        }
    }

}
*/
class TicketRunnable implements Runnable{

    private int ticketNum = 5;
    @Override
    public void run() {
        while(true){
            System.out.println("第" + Thread.currentThread().getName() + "线程卖出第" + ticketNum -- + "张票");
            if(ticketNum < 1){
                break;
            }
        }
    }
}
