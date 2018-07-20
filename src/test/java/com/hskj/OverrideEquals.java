package com.hskj;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by hongHan_gao
 * Date: 2018/7/20
 * 对equals进行重写，判断集合中是否存在指定对象
 */


public class OverrideEquals {

    @Test
    public void overrideEqualsTest(){

        Collection<Emp> emps = new ArrayList<>();
        emps.add(new Emp("Eric", 8000));
        emps.add(new Emp("Smith", 10000));
        emps.add(new Emp("Lucy", 8000));
        emps.add(new Emp("Jack", 6000));

        System.out.println(emps.contains(new Emp("Eric", 8000)));
    }
}

class Emp{

    private String name;
    private double salary;

    public Emp(String name, double salary){
        this.name = name;
        this.salary = salary;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }

    public double getSalary(){
        return this.salary;
    }

    @Override
    public boolean equals(Object obj) {
        if(null == obj){
            return false;
        }else if(this == obj){
            return true;
        }else if(obj instanceof Emp){
            Emp emp = (Emp)obj;
            return this.getName().equals(emp.getName()) && this.getSalary() == emp.getSalary();
        }else{
            return false;
        }
    }
}
