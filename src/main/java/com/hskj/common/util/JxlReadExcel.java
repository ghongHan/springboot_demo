package com.hskj.common.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;

/**
 * JXL解析excel
 * Created by hongHan_gao
 * Date: 2017/12/21
 */

public class JxlReadExcel {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //找到excel文件
            File file = new File("e:/test.xls");
            //创建工作簿
            Workbook workbook = Workbook.getWorkbook(file);
            //找到sheet
            Sheet sheet = workbook.getSheet(0);
            //遍历行列，获取数据
            for (int i = 0; i < sheet.getRows(); i++) {
                Cell cell = null;
                for (int j = 0; j < sheet.getColumns(); j++) {
                    cell = sheet.getCell(j, i);
                    if (cell.getContents().contains("/")) {
                        String[] values = cell.getContents().split("/");
                        for (int k = 0; k < values.length; k++) {
                            stringBuilder.append(values[k]);
                            if (k != values.length - 1) {
                                stringBuilder.append(",");
                            }
                        }
                        System.out.println();
                    } else {
                        if(cell.getContents().contains("USPH") && i != 0){
                            stringBuilder.append(";");
                            stringBuilder.append(sheet.getCell(j, cell.getRow()).getContents());
                            stringBuilder.append(",");
                        }else{
                            stringBuilder.append(sheet.getCell(j, cell.getRow()).getContents());
                            if(sheet.getCell(j, cell.getRow()).getContents() != "" ){
                                stringBuilder.append(",");
                            }
                        }

                        System.out.println(sheet.getCell(j, cell.getRow()).getContents());
                    }
                }
            }
            System.out.print("stringBuilder ====" + stringBuilder.toString());
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}



