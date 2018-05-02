package com.hskj;

import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by hongHan_gao
 * Date: 2018/3/9
 * 反射API中几个基本方法
 */


public class ReflectionDemo  {

    public static void main(String[] args) throws Exception {
        //获取Employee这个类所关联的Class对象
        Class classType = Class.forName("com.hskj.Employee");
        //通过反射机制构造一个Employee的实例对象（默认调用无参构造方法）
        Employee employee = (Employee) classType.newInstance();

        System.out.println("********调用制定的构造方法来构造对象(无参构造方法)******");
        //调用制定的构造方法来构造对象(无参构造方法)
        Constructor constructor = classType.getConstructor(new Class[]{});
        Employee newEmployee = (Employee) constructor.newInstance(new Object[]{});
        System.out.println(newEmployee);


        System.out.println("*******调用指定的构造方法来构造对象（有参构造方法*********");
        //调用指定的构造方法来构造对象（有参构造方法）
        Constructor argsContructor = classType.getConstructor(new Class[]{String.class, Integer.class});
        Employee dNewEmployee = (Employee) argsContructor.newInstance(new Object[]{"liHao", 10});
        System.out.println(dNewEmployee);
        //获取Class对象指定的方法，包括私有
        Method method = classType.getDeclaredMethod("toString", new Class[]{});
        System.out.println("方法名：" + method.getName());
        //方法的调用
        String result = (String) method.invoke(dNewEmployee, new Object[]{});
        System.out.println(result);
    }
}
@Data
class Employee{

    private String name;

    private Integer age;

    public Employee(){
        System.out.println("无参构造方法");
    }

    public Employee(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}