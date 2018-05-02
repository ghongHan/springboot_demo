package com.hskj;

/**
 * Created by hongHan_gao
 * Date: 2018/3/6
 */


public class TestSingleton {

    public static void main(String[] args){
        for(int i = 0; i<10000; i++){
            Thread thread = new Thread(new SingletonThread());
            thread.setName("thread" + i);
            thread.start();
        }

    }

    static class SingletonThread implements Runnable{

        @Override
        public void run() {
            Singleton.getInstance();
        }
    }
}
