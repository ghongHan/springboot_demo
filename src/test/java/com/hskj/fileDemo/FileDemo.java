package com.hskj.fileDemo;

import org.junit.Test;

import java.io.File;

/**
 * Created by hongHan_gao
 * Date: 2018/7/27
 */


public class FileDemo {

    @Test
    public void fileTest(){
        File file = new File("../resources/cert/apiclient_cert.p12");
        System.out.println(this.getClass().getResource(""));
        System.out.println(this.getClass().getResource("/"));
        System.out.println(this.getClass().getResource("."));
        Character a = 'a';
        System.out.println(a.toString().getBytes().length);
        System.out.println(file.isFile());
        System.out.println((int)(file.length()/1024) + 1);

    }

}
