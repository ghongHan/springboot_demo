package com.hskj.common.util;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 字符串工具
 * Created by Administrator on 2017/12/15.
 */
public class StringUtil {

    /**
     * 判断字符串是空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if("".equals(str)|| str==null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断字符串不是空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        if(!"".equals(str)&&str!=null){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 判断某一个字符串数组中是否含有某一字符串
     * @param str
     * @param strArr
     * @return
     */
    public static boolean existStrArr(String str,String []strArr){
        return Arrays.asList(strArr).contains(str);
    }

    /**
     * String[]转int[]
     * @param arrs
     * @return
     */
    public static Integer[] stringArrToIntegerArr(String[] arrs){
        Integer[] ints = new Integer[arrs.length];
        for(int i=0;i<arrs.length;i++){
            ints[i] = Integer.parseInt(arrs[i]);
        }
        return ints;
    }

    /**
     * String[]转Set<Long> 并去除重复元素
     * Long[] 可有Set<Long>.toArray()取得
     * @param arrs
     * @return
     */
    public static Set<Long> stringArrToLongSet(String[] arrs){
        Set<Long> longSet = new HashSet<Long>();
        for(int i=0;i<arrs.length;i++){
            longSet.add(Long.parseLong(arrs[i]));
        }
        return longSet;
    }

    /**
     * 判断数组是否为空数组，并取出数组中的空元素
     * @param params
     * @return
     */
    public static boolean stringArrIsEmpty(String[] params){
        String[] arrays = removeEmptyElement(params);
        System.out.print(arrays);
        return (null == arrays || arrays.length == 0);
    }

    /**
     * 取出数组中的空元素
     * @param params
     * @return
     */
    private static String[] removeEmptyElement(String[] params) {
        if(null == params || params.length == 0){
            return null;
        }else{
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < params.length; i++){
                if(Strings.isNullOrEmpty(params[i])){
                    continue;
                }
                stringBuilder.append(params[i]);
                if(i != params.length){
                    stringBuilder.append(";");
                }
            }
            return stringBuilder == null? null : stringBuilder.toString().split(";");
        }
    }

    //将对象中类型为String且属性值为null的，转化为空字符串
    public static Object nullEmpty(Object object) throws Exception {
        Class classT = object.getClass();
        Field[] fields = classT.getDeclaredFields();
        for (Field field : fields) {
            if (String.class == field.getType()) {
                //将属性值首字母转化成大写
                char[] chars = field.getName().toCharArray();
                chars[0] -= 32;
                String methodGetName = "get" + String.valueOf(chars);
                String methodSetName = "set" + String.valueOf(chars);
                Method getMethod = classT.getMethod(methodGetName, null);
                Method setMethod = classT.getMethod(methodSetName, String.class);
                Object value = getMethod.invoke(object, null);
                if (StringUtils.equalsAny(String.valueOf(value), null, "NULL", "null")) {
                    setMethod.invoke(object, "");
                }
            }
        }
        return object;
    }

    public static void main(String[] args){
        String[] params = {"", "hello", "", "world"};
        stringArrIsEmpty(params);
    }
}
