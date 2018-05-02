package com.hskj.common.util;

import com.google.common.base.Strings;
import jxl.Workbook;
import jxl.format.*;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import java.io.File;

/**
 * JXL创建excel
 * Created by hongHan_gao
 * Date: 2017/12/21
 */

public class ExcelByJxl {
    private static final String[] title = {"序号", "姓名", "性别"};

    public static void createExcel(){
        try {
            //创建excel文件
            File file = new File("e:/jxl_test_" + System.currentTimeMillis() + ".xls");
            if (!file.exists()) {
                file.createNewFile();
            }
            //创建工作簿
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            //创建sheet
            WritableSheet sheet = workbook.createSheet("sheet1", 0);

            //合并单元格：第一行第一列到第一行第三列合并
            sheet.mergeCells(0, 0, 2, 0);
            //设置单元格高度：设定第一行高2000，第一列高30
            sheet.setRowView(0, 600);
            sheet.setColumnView(0, 20);

            /**
             *  指定字体样式：字体TIMES,字号16,加粗显示。
             *  字体 WritableFont.TIMES
             *  大小 16
             *  是否为粗体 WritableFont.BOLD WritableFont.NO_BOLD
             *  是否为斜体 true
             *  样式 UnderlineStyle.NO_UNDERLINE 下划线
             *  颜色 jxl.format.Colour.RED 字体颜色为红色
             */
            WritableFont font = new WritableFont(WritableFont.TIMES, 16,
                    WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.RED);

            /**
             * WritableCellFormat类，通过它可以指定单元格的各种属性
             * 指定数据对齐方式 水平对齐 垂直对齐
             */
            WritableCellFormat format = new WritableCellFormat(font);
            format.setAlignment(Alignment.RIGHT);
            format.setVerticalAlignment(VerticalAlignment.CENTRE);

            Label label = null;
            label = new Label(0, 0, "统一测试表", format);
            sheet.addCell(label);

            //第二行设置列名
            for (int i = 0; i < title.length; i++) {
                label = new Label(i, 1, title[i]);
                sheet.addCell(label);
            }
            //追加数据
            for (int i = 2; i < 10; i++) {
                label = new Label(0, i, i + "");
                sheet.addCell(label);
                label = new Label(1, i, "name" + i);
                sheet.addCell(label);
                label = new Label(2, i, "age" + i);
                sheet.addCell(label);
            }

            /**
             * 插入图片
             * WritableImage(col, row, width, height, imgFile)
             * col row 是图片的起始行起始列 width height是定义图片跨越的行数与列数
             */
            String imgPath = "D:/image.png";
            File imgFile = new File(imgPath);
            WritableImage image = new WritableImage(0, 0, 1, 1, imgFile);
            sheet.addImage(image);

            workbook.write();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createExcel();
    }

}
