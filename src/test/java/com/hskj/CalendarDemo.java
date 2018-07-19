package com.hskj;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by hongHan_gao
 * Date: 2018/7/18
 * 时间处理类
 */


public class CalendarDemo {

    @Test
    public void calendarTest(){

        Calendar c = Calendar.getInstance();
        System.out.println(c.get(Calendar.MONTH));
        GregorianCalendar gc = new GregorianCalendar(2018,Calendar.AUGUST,19);
        System.out.println(gc.get(Calendar.DAY_OF_WEEK));
    }

    @Test
    public void getActualMaximum(){
        //Calendar为抽象类
        //今天
        Calendar c = Calendar.getInstance();
        int maxDate = c.getActualMaximum(Calendar.YEAR);
        System.out.println("本月一共有：" + maxDate + "天");

    }
}
