package com.hskj.threadDemo;

/**
 * Created by hongHan_gao
 * Date: 2018/6/7
 * 比较俩种创建线程；推荐使用实现Runnable接口的方式：避免单继承的局限性、是实现的数据共享
 */


public class SharedDataThreadDemo {

    public static void main(String[] args){
/*
        Thread aThread = new TicketThread("一号窗口");
        aThread.start();
        Thread bThread = new TicketThread("二号窗口");
        bThread.start();
*/

        Runnable runnable = new TickerRunnable();
        Thread cThread = new Thread(runnable, "一号窗口");
        cThread.start();
        Thread dThread = new Thread(runnable, "二号窗口");
        dThread.start();

    }

}

class TicketThread extends Thread{

    //定义票的数量
    private Integer ticketNum = 5;

    public TicketThread(String name){
        super(name);
    }

    @Override
    public void run() {
        while(true){
                if(ticketNum < 1){
                    break;
                }
                System.out.println(this.getName() + ":" + (ticketNum--));
        }
    }
}

class TickerRunnable implements Runnable{

    //票的数量
    private Integer ticketNum = 5;

    @Override
    public void run() {
        while(true){
            if(ticketNum < 1){
                break;
            }
            System.out.println(Thread.currentThread().getName() + ":" + (ticketNum--));
        }
    }
}
