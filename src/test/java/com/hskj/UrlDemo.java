package com.hskj;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hongHan_gao
 * Date: 2018/5/24
 * URL读取网页资源
 */


public class UrlDemo {

    public static void main(String[] args) throws Exception {

        URL url = new URL("https://www.cnblogs.com/stephen-liu74/archive/2012/06/06/2312759.html");
        //获取读取的输入字节流
        InputStream inputStream = url.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String data = null;
        StringBuilder stringBuilder = new StringBuilder();
        //循环读取数据
        while((data = bufferedReader.readLine()) != null){
            stringBuilder.append(data.trim());
        }
        System.out.println(stringBuilder.toString());
    }

}
