package com.hskj.common.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * poi 创建Excel文件
 * Created by hongHan_gao
 * Date: 2017/12/21
 */

public class ExcelByPoi {

    //第一行内容
    private static final String[] title = {"序号", "姓名", "性别"};

    public static void createExcel(){
        //创建Excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个工作表sheet
        HSSFSheet sheet = workbook.createSheet("测试表");

        /**************************设置标题样式**************************/
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        // 设置单元格的横向和纵向对齐方式
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置第一行字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setColor(HSSFColor.GREEN.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        headerStyle.setFont(font);
        /**************************设置标题样式**************************/

        //创建第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("测试统一浏览表");

        //将样式应用于指定单元格
        cell.setCellStyle(headerStyle);
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

        HSSFRow secondRow = sheet.createRow(1);
        //插入第二行数据id，name，sex
        for (int i = 0; i < title.length; i++) {
            HSSFCell newCell = secondRow.createCell(i);
            newCell.setCellValue(title[i]);
        }
        //从第三行开始插入数据
        for (int i = 2; i < 10; i++) {
            HSSFRow nextRow = sheet.createRow(i);
            HSSFCell firstCell = nextRow.createCell(0);
            firstCell.setCellValue(i - 1 + "");
            HSSFCell secondCell = nextRow.createCell(1);
            secondCell.setCellValue("name" + (i - 1));
            HSSFCell thirdCell = nextRow.createCell(2);
            thirdCell.setCellValue("男");
        }
        //创建一个文件
        File file = new File("e:/poi_test.xls");
        if (!file.exists()) {
            try {
                file.createNewFile();
                //将Excel写入到e:/poi_test.xls
                FileOutputStream stream = FileUtils.openOutputStream(file);
                workbook.write(stream);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        createExcel();
    }
}
