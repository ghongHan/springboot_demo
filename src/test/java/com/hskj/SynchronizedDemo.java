package com.hskj;

/**
 * Created by hongHan_gao
 * Date: 2018/3/10
 * 使用synchronized加同步锁，模拟银行取款
 */


public class SynchronizedDemo {

    public static void main(String[] args){
        Bank bank = new Bank();
        Thread t1 = new BankThread(bank);
        t1.start();
        Thread t2 = new BankThread(bank);
        t2.start();
    }

}
class BankThread extends Thread{
    private Bank bank;

    public BankThread(Bank bank){
        this.bank = bank;
    }

    @Override
    public void run() {
        bank.getMoney(400);
    }
}

class Bank{

    private int sumMoney = 500;

    public synchronized int getMoney(int takeMoney){
        if(takeMoney < 0){
            System.out.println("取款金额不能为负数");
            return -1;
        }else if(sumMoney < 0){
            System.out.println("账户金额不能为负数");
            return -2;
        }else if(sumMoney - takeMoney < 0){
            System.out.println("取款金额不能超过账户余额");
            return -3;
        }else{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sumMoney -= takeMoney;
            System.out.println("账户余额：" + sumMoney);
            return takeMoney;
        }
    }
}
