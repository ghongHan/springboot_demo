package com.hskj;

/**
 * 静态内部类：
 *  (1)与类的其他成员相似，可以用static修饰内部类，这样的类称为静态内部类。静态内部类与静态内部方法相似，
 * 只能访问外部类的static成员，不能直接访问外部类的实例变量，与实例方法，只有通过对象引用才能访问。
 *  (2)由于static内部类不具有任何对外部类实例的引用，因此static内部类中不能使用this关键字来访问外部类中的实例成员，
 * 但是可以访问外部类中的static成员。这与一般类的static方法想通
 * Created by hongHan_gao
 * Date: 2018/2/23
 */


public class Outer2 {

    public static int num = 10;
    public String temp = "world";

    public static class Inner{
        private String word = "hello";
        public void innerMethod(){
            System.out.println("num = " + num);
            System.out.println("word = " + word);
            System.out.println("temp = " + new Outer2().temp);
        }
    }

    public static void main(String[] args) {
        //静态内部类不通过外部实例就可以创建对象；与类变量可以通过类名访问相似
        Inner inner = new Inner();
        inner.innerMethod();

    }

}
