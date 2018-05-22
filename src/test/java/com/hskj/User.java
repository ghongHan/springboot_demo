package com.hskj;

/**
 * Created by hongHan_gao
 * Date: 2018/5/19
 */


public class User {

    private String name;

    private long age;

    private String sex;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "{'name':" + name + ",'age':" + age + ",'sex':" + sex + "}";
    }
}
