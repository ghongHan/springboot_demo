package com.hskj;




import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hongHan_gao1·
 * Date: 2018/5/19
 */


public class BeanUtils_copyProperties {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        Map<String, Object> paramMap = new TreeMap<>();
        paramMap.put("name", "lucy");
        paramMap.put("age", 19);
        paramMap.put("sex", "女");
        BeanUtils.copyProperties(user, paramMap);
        System.out.println(user);

        User person = new User();
        org.springframework.beans.BeanUtils.copyProperties(paramMap, person);
        System.out.println(person);
    }

}
