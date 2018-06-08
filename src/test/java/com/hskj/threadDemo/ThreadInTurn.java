package com.hskj.threadDemo;

import java.util.LinkedList;

/**
 * Created by hongHan_gao
 * Date: 2018/6/8
 * 让线程有顺序的执行；
 * <p>
 * 功能描述：
 *         生产者每生产五个苹果就通知消费者进行消费，消费者每消费五个苹果就通知生产者生产，
 *         苹果放于篮子中，若篮子中有五个苹果，则生产者等待，消费者消息；
 *         若篮子中不足或没有五个苹果，则生产者生产，消费者等待。
 */


public class ThreadInTurn {

    public static void main(String[] args) {
        Basket basket = new Basket();
        Thread producer = new Producer(basket);
        producer.start();
        Thread consumer = new Consumer(basket);
        consumer.start();
    }

}

/******苹果******/
class Apple {

    public Integer id;

    public Apple(Integer id) {
        this.id = id;
    }
}

/******果篮******/
class Basket {

    private LinkedList<Apple> basket = new LinkedList<>();

    /**
     * 放苹果
     */
    public synchronized void pushApple(){
        for(int i = 1; i < 21; i++){
            Apple apple = new Apple(i);
            push(apple);
        }
    }

    /**
     * 取苹果
     */
    public synchronized void popApple(){
        for(int i = 1; i < 21; i++){
            pop();
        }
    }

    /**
     * 放苹果操作
     * @param apple
     */
    public void push(Apple apple) {
        try {
            if (basket.size() == 5) {
                wait();
            }
            Thread.sleep(500);
            basket.addFirst(apple);
            System.out.println("生产出第" + apple.id + "苹果");
            //通知消费者来消费
            notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取苹果操作
     */
    public void pop(){
        try {
            if(basket.size() == 0){
                wait();
            }
            Thread.sleep(500);
            Apple apple = basket.removeFirst();
            System.out.println("消费掉第：" + apple.id + "苹果");
            //通知生产者生产
            notify();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

/*****生产者*****/
class Producer extends Thread{

    private Basket basket;

    public Producer(Basket basket){
        this.basket = basket;
    }

    @Override
    public void run() {
        basket.pushApple();
    }
}

/*****消费者*****/
class Consumer extends Thread{

    private Basket basket;

    public Consumer(Basket basket){
        this.basket = basket;
    }

    @Override
    public void run() {
        basket.popApple();
    }
}

