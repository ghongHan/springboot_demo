package com.hskj;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongHan_gao
 * Date: 2018/3/8
 */


public class ArrayListDemo2 {

    public static void main(String[] args) {
        List<Student> arrayList = new ArrayList<>();
        Student stu1 = new Student("liHao", 20);
        Student stu2 = new Student("zhangBao", 20);
        Student stu3 = new Student("liXiang", 19);
        Student stu4 = new Student("Wuhu", 30);
        Student stu5 = new Student("liuZhi", 26);
        arrayList.add(stu1);
        arrayList.add(stu2);
        arrayList.add(stu3);
        arrayList.add(stu4);
        arrayList.add(stu5);
        System.out.println(arrayList.size());
        Student stu6 = new Student("liHao", 20);
        System.out.println(arrayList.indexOf(stu6));
    }

}

@Data
class Student {
    private String name;

    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (!name.equals(student.name)) return false;
        return age.equals(student.age);
    }
}