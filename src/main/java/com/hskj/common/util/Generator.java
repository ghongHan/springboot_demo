package com.hskj.common.util;

import java.util.Random;

/**
 * Created by hongHan_gao
 * Date: 2018/1/5
 */


public class Generator {

    /**
     * 获取6位随机数
     * @return
     */
    public static String randomCode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(new Random().nextInt(10));
        }
        return stringBuilder.toString();
    }

}
