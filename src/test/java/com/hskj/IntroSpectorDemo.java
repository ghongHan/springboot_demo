package com.hskj;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Created by hongHan_gao
 * Date: 2018/5/18
 * 內省机制示例
 */


public class IntroSpectorDemo {

    public static void main(String[] args) throws Exception {
        //创建User对象
        IntroSpectorDemo.User user = new IntroSpectorDemo().new User();
        //获取javaBean的描述对象
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        //获取javaBean中的属性的描述器
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for(PropertyDescriptor descriptor : descriptors){
            //获取当前javaBean的属性
            System.out.println("属性名：" + descriptor.getName());
            //获取当前属性的getter方法
            System.out.println("getter:" + descriptor.getReadMethod());
            //获取当前属性的setter方法
            System.out.println("setter:" + descriptor.getWriteMethod());
            if("name".equals(descriptor.getName())){
                descriptor.getWriteMethod().invoke(user, "Tom");
            }
        }
        System.out.println(user);
    }


    class User{
        private String name;

        private long age;

        private boolean isMan;

        public void setName(String name) {
            this.name = name;
        }

        public long getAge() {
            return age;
        }


        public boolean isMan() {
            return isMan;
        }

        public void setMan(boolean man) {
            isMan = man;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", isMan=" + isMan +
                    '}';
        }
    }
}
