package com.hskj.common.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.IOException;

/**
 * POI解析excel
 * Created by hongHan_gao
 * Date: 2017/12/21
 */


public class PoiReadExcel {

    public static void main(String[] args) {

        try {
            //找到excel文件
            File file = new File("e:/poi_test.xls");
            //创建工作簿
            HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
            //默认第一个sheet工作表
            HSSFSheet sheet = workbook.getSheetAt(0);
            //定义第一行行号
            int firstRowNum = 0;
            //获取sheet工作表的最后一行行号
            int lastRowNum = sheet.getLastRowNum();
            for (int i = firstRowNum; i <= lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                //获取当前行的最后一个单元格的序号
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    HSSFCell cell = row.getCell(j);
                    String value = cell.getStringCellValue();
                    System.out.print(value + " ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
