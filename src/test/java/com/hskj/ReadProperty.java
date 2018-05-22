package com.hskj;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by hongHan_gao
 * Date: 2018/5/17
 * 读取配置文件
 */


public class ReadProperty {

    public static void main(String... args){
        System.out.println(getProperty("/config/prop.properties", "csport"));

    }

    public static String getProperty(String path, String key){
        Properties prop = null;
        try {
            prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }


}



