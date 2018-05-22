package com.hskj;

import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongHan_gao
 * Date: 2018/5/18
 * javaBean与map之间的相互转化
 */


public class BeanMapExchange {

     public static void main(String[] args) throws Exception {
         User user = new BeanMapExchange().new User();
         user.setName("Tom");
         user.setAge(10);
         user.setMan(true);
         System.out.println(bean2Map(user));
         Map<String, Object> paramMap = new HashMap<>();
         paramMap.put("name", "Jack");
         paramMap.put("age", 18);
         paramMap.put("isMan", true);
         System.out.println((User)map2Bean(paramMap, User.class));

     }

     public static Map<String, Object> bean2Map(Object bean) throws Exception {
         Map<String, Object> resultMap = new HashMap();
         BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
         PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
         for(PropertyDescriptor propertyDescriptor : propertyDescriptors){
             String key = propertyDescriptor.getName();
             Object value = propertyDescriptor.getReadMethod().invoke(bean);
             resultMap.put(key, value);
         }
         return resultMap;
     }

     public static <T> T map2Bean(Map<String, Object> map, Class<T> beanType) throws Exception {
         //创建bean对象
         //Object bean  = beanType.newInstance();
         Object bean  = new BeanMapExchange().new User();
         BeanInfo beanInfo = Introspector.getBeanInfo(beanType, Object.class);
         PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
         for(PropertyDescriptor propertyDescriptor : propertyDescriptors){
             String key = propertyDescriptor.getName();
             Method setter = propertyDescriptor.getWriteMethod();
             switch(key){
                 case "name":
                     setter.invoke(bean, map.get(key));
                     break;
                 case "age":
                     setter.invoke(bean, map.get(key));
                     break;
                 case "isMan":
                     setter.invoke(bean, map.get(key));
                     break;
             }
         }
         return (T)bean;
     }

     class User{
         private String name;

         private long age;

         private boolean isMan;

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
