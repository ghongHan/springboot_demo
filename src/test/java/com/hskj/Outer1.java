package com.hskj;

/**
 * 常规内部类：常规内部类没有用static修饰且定义在在外部类类体中。
 * 1.常规内部类中的方法可以直接使用外部类的实例变量和实例方法。
 * 2.在常规内部类中可以直接用内部类创建对象
 * Created by hongHan_gao
 * Date: 2018/2/23
 */


public class Outer1 {

    private int num = 10;

    //常规内部类
    class Inner{
        private String word = "hello";

        public void innerMethod(){
            System.out.println("内部类中 word = " + word);
            outMethod();
            System.out.println("外部类中的 num = " + num);
        }
    }

    public void outMethod(){
        num++;
    }

    //在外部类方法中创建内部类
    public Inner makeInner(){
        Inner in = new Inner();
        return in;
    }

    public static void main(String [] args) {
        Outer1 outer = new Outer1();
        //常规内部类需要通过外部类的实例才能创建对象，与实例变量需要通过对象来访问相似
        //Outer.Inner inner = outer.new Inner();
        Inner inner = outer.makeInner();
        inner.innerMethod();
    }
}
