package com.hskj.common.util;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.*;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.Boolean;
import java.lang.Number;
import java.lang.reflect.Field;
import java.util.List;


/**
 * <br>
 * <b>功能：</b>excel导出工具类<br>
 * <b>作者：</b>Pan.ShiJu<br>
 * <b>日期：</b>2016年7月5日下午2:56:34<br>
 */
public class ExcelUtil {

    // 每个sheet最多放65536行
    private static final int MAX_ROWS = (1 << 16) - 100;

    // 标题开始行数
    private static final int TITLE_START_ROW = 7;

    private static final String SHEET_NAME = "Sheet";

    /*=================================response头设置=======================================*/
    private static final String CHARACTER_ENCODING = "UTF-8";
    //    private static final String CONTENT_TYPE = "ContentType";
    private static final String CONTENT_TYPE = "APPLICATION/OCTET-STREAM";
    private static final String CONTENT_DISPOSITION_KEY = "Content-disposition";
    private static final String CONTENT_DISPOSITION_VALUE_PREF = "attachment; filename=";

    /*===================== 字体大小 ====================*/
    private static final int FONT_SIZE_10 = 10;
    private static final int FONT_SIZE_12 = 12;
    private static final int FONT_SIZE_14 = 14;
    private static final int FONT_SIZE_16 = 16;
    private static final int FONT_SIZE_18 = 18;
    private static final int FONT_SIZE_20 = 20;


    /*===================== 宋体 ====================*/
    public static final WritableFont.FontName MY_ARIAL = WritableFont.createFont("宋体");

    /*===================== 行数 列数====================*/
    private static final int FIRST_ROW = 0;
    private static final int SECOND_ROW = 1;
    private static final int THIRD_ROW = 2;
    private static final int FOURTH_ROW = 3;
    private static final int FIFTH_ROW = 4;
    private static final int SIX_ROW = 5;
    private static final int SEVENTH_ROW = 6;
    private static final int EIGHTH_ROW = 7;
    private static final int NINTH_ROW = 8;

    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;
    private static final int THIRD_COLUMN = 2;
    private static final int FOURTH_COLUMN = 3;
    private static final int FIFTH_COLUMN = 4;
    private static final int SIX_COLUMN = 5;
    private static final int SEVENTH_COLUMN = 6;
    private static final int EIGHTH_COLUMN = 7;
    private static final int NINTH_COLUMN = 8;

    /**
     * =======================图片跨越的行数与列数========================
     */
    private static final int IMAGE_WIDTH = 2;
    private static final int IMAGE_HEIGHT = 2;


    /**
     * =======================单元格行高========================
     */
    private static final int ROW_HEIGHT = 500;

    /**
     * ====时间字符串 格式 ====
     */

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * <br>
     * <b>功能：</b>数据导出<br>
     * <b>作者：</b>Pan.ShiJu<br>
     * <b>日期：</b>2016年7月5日下午2:57:25<br>
     *
     * @param fileName        文件名称
     * @param firstTitleName  第一个标题
     * @param secondTitleName 第二个标题
     * @param sixRowInfo      第6行数据数组
     * @param contentTitle    正文标题数组
     * @param columnWidth     正文每列宽度
     * @param listContent     正文List集合
     * @param totalRowInfo    统计行数组
     * @param response        响应
     * @return
     */
    public final static boolean exportDataToExcel(
            String fileName,
            String firstTitleName,
            String secondTitleName,
            String[] sixRowInfo,
            String[] contentTitle,
            int[] columnWidth,
            List listContent,
            String[] totalRowInfo,
            HttpServletResponse response) {

        boolean result = true;              // 返回结果

        OutputStream os = null;             // 定义输出流
        WritableWorkbook workbook = null;   // 工作簿
        try {
            // 定义输出流，以便打开保存对话框 begin
            os = response.getOutputStream();// 取得输出流

            // 设置输出头信息
            setResponseInfo(response, fileName);

            /** **********创建工作簿************ */
            workbook = Workbook.createWorkbook(os); // 工作簿，也就是文件本身

            WritableSheet sheet = null; // 工作表

            //导出的总行数，超过65536-100，换一页
            int rowCount = listContent.size();
            for (int i = 0; i < rowCount / MAX_ROWS + 1; i++) {

                // 开始导出位置
                int fromIndex = i * MAX_ROWS;
                if (fromIndex > rowCount) {
                    break;
                }
                // 结束导出位置
                int toIndex = (i + 1) * MAX_ROWS;
                toIndex = toIndex > rowCount ? rowCount : toIndex;

                /** **********创建工作表************ */
                sheet = workbook.createSheet(SHEET_NAME + i, i);

                /** **********设置纵横打印（默认为纵打）、打印纸***************** */
                sheet.getSettings().setProtected(false);

                /** 正文单元格样式 */
                WritableCellFormat wcfCenter = getCenterNoBoldCellFormat(FONT_SIZE_14);
                WritableCellFormat wcfDF = getLeftNoBoldDateCellFormat(FONT_SIZE_14);

                // 正文数据
                List<Object> listContentTemp = null;
                if (rowCount < MAX_ROWS) {
                    listContentTemp = listContent;
                } else {
                    listContentTemp = listContent.subList(fromIndex, toIndex);
                }

                // 第一个sheet才有头部信息
                if (i == 0) {
                    // 处理第一个sheet
                    firstSheetHandle(sheet, firstTitleName, secondTitleName, sixRowInfo, contentTitle, columnWidth);
                    /** ***************以下是正文行列标题********************* */
                    addCellFromTitle(sheet, contentTitle, columnWidth, EIGHTH_ROW, wcfCenter);
                    // 正文信息循环添加
                    loopAddCellFromListContent(sheet, listContentTemp, NINTH_ROW, FIRST_COLUMN, wcfCenter, wcfDF);
                } else {
                    /** ***************以下是正文行列标题********************* */
                    addCellFromTitle(sheet, contentTitle, columnWidth, FIRST_ROW, wcfCenter);
                    // 正文信息循环添加
                    loopAddCellFromListContent(sheet, listContentTemp, FIRST_ROW, FIRST_COLUMN, wcfCenter, wcfDF);
                }
            }
            // 添加一行合计信息
            addTotalRowInfo(sheet, columnWidth, totalRowInfo, sheet.getRows());

            /** **********将以上缓存中的内容写到EXCEL文件中******** */
            workbook.write();

        } catch (Exception e) {
            result = false;
        } finally {
            /** *********关闭文件************* */
            closeStream(workbook, os);
        }
        return result;
    }


    /**
     * 第一个sheet操作
     *
     * @param sheet
     * @param firstTitleName
     * @param secondTitleName
     * @param sixRowInfo
     * @param contentTitle
     * @param columnWidth
     * @throws WriteException
     * @throws IllegalAccessException
     */
    private static void firstSheetHandle(
            WritableSheet sheet,
            String firstTitleName,
            String secondTitleName,
            String[] sixRowInfo,
            String[] contentTitle,
            int[] columnWidth) throws WriteException, IllegalAccessException {

        int columnLength = contentTitle.length - 1;

        /* **************第一个标题和图片 ********************/
        // 合并第一行到第二行
        mergeFirstCell(sheet, firstTitleName, columnLength);
        sheet.setRowView(FIRST_ROW, ROW_HEIGHT);
        // 添加图片
        //addImage(sheet);

        /* **************第二个标题 ********************/
        // 合并第三行到第五行
        mergeSecondCell(sheet, secondTitleName, columnLength);
        sheet.setRowView(THIRD_ROW, ROW_HEIGHT);

        // 第六行插入数据
        addCellSixRow(sixRowInfo, columnWidth, sheet, SIX_ROW);

        // 第7行合并
        mergeSeventhCell(sheet, columnLength);
        sheet.setRowView(SEVENTH_ROW, ROW_HEIGHT);

    }


    /**
     * 合并第一行到第二行，第一个大标题
     *
     * @param sheet
     * @param firstTitleName
     * @param columnLength
     * @throws WriteException
     */
    private static void mergeFirstCell(WritableSheet sheet, String firstTitleName, int columnLength) throws WriteException {
        WritableCellFormat wcfCenter = getCenterNoBoldCellFormat(FONT_SIZE_18);
        sheet.mergeCells(FIRST_COLUMN, FIRST_ROW, columnLength, SECOND_ROW);
        Label label = new Label(FIRST_COLUMN, FIRST_ROW, firstTitleName, wcfCenter);
        sheet.addCell(label);
    }

    /**
     * 添加图片
     *
     * @param sheet
     */
    private static void addImage(WritableSheet sheet) {
        File f = new File(ExcelUtil.class.getResource("/").getPath());
        String imgPath = f.getPath() + File.separator + "static" + File.separator + "img" + File.separator + "gong_xin_logo.png";

        File imgFile = new File(imgPath);
        //col row是图片的起始行起始列  width height是定义图片跨越的行数与列数
        sheet.addImage(new WritableImage(FIRST_COLUMN, FIRST_ROW, IMAGE_WIDTH, IMAGE_HEIGHT, imgFile));
    }


    /**
     * 合并第三行到第五行，显示第二个大标题
     *
     * @param sheet
     * @param secondTitleName
     * @param columnLength
     */
    private static void mergeSecondCell(WritableSheet sheet, String secondTitleName, int columnLength) throws WriteException {
        WritableCellFormat wcfCenter = getCenterNoBoldCellFormat(FONT_SIZE_20);
        sheet.mergeCells(FIRST_COLUMN, THIRD_ROW, columnLength, FIFTH_ROW);
        Label label = new Label(FIRST_COLUMN, THIRD_COLUMN, secondTitleName, wcfCenter);
        sheet.addCell(label);
    }

    /**
     * 第6行
     * @param sixRowInfo
     * @param columnWidth
     * @param sheet
     * @param startRow
     * @throws WriteException
     */
    private static void addCellSixRow(String[] sixRowInfo, int[] columnWidth, WritableSheet sheet, int startRow) throws WriteException {
        WritableCellFormat wcfBoldCenter = getCenterBoldCellFormat(FONT_SIZE_14);
        WritableCellFormat wcfNoBoldCenter = getCenterNoBoldCellFormat(FONT_SIZE_14);
        for (int i = 0; i < sixRowInfo.length; i++) {
            sheet.setColumnView(i, columnWidth[i]);
            if (i % 2 == 0) {
                sheet.addCell(new Label(i, startRow, sixRowInfo[i], wcfBoldCenter));
            } else {
                sheet.addCell(new Label(i, startRow, sixRowInfo[i], wcfNoBoldCenter));
            }
        }
    }

    /**
     * 第7行合并
     *
     * @param sheet
     */
    private static void mergeSeventhCell(WritableSheet sheet, int columnLength) throws WriteException {
        WritableCellFormat wcfCenter = getCenterBoldCellFormat(FONT_SIZE_14);
        sheet.mergeCells(FIRST_COLUMN, SEVENTH_ROW, columnLength, SEVENTH_ROW);
        sheet.addCell(new Label(FIRST_COLUMN, SEVENTH_ROW, "", wcfCenter));
    }

    /**
     * 最后一行统计
     *
     * @param sheet
     * @param columnWidth
     * @param totalRowInfo
     * @param startRow
     * @throws WriteException
     */
    private static void addTotalRowInfo(WritableSheet sheet, int[] columnWidth, String[] totalRowInfo, int startRow) throws WriteException {
        WritableCellFormat wcf = getRedCenterBoldCellFormat(FONT_SIZE_14);
        for (int i = 0; i < totalRowInfo.length; i++) {
            sheet.setColumnView(i, columnWidth[i]);
            sheet.addCell(new Label(i, startRow, totalRowInfo[i], wcf));
        }
    }

    /** ******************************* 公共方法 start ************************************/

    /**
     * 设置输出头信息
     *
     * @param response
     * @param fileName
     */
    private static void setResponseInfo(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.reset();// 清空输出流
        response.setCharacterEncoding(CHARACTER_ENCODING);
        String responseHeader = CONTENT_DISPOSITION_VALUE_PREF + new String(fileName.getBytes("gbk"), "iso8859-1");
        // 设定输出文件头
        response.setHeader(CONTENT_DISPOSITION_KEY, responseHeader);
        // 定义输出类型
        response.setContentType(CONTENT_TYPE);
    }

    /**
     * @param workbook
     * @param os
     */
    private static void closeStream(WritableWorkbook workbook, OutputStream os) {
        try {
            if (workbook != null) {
                workbook.close();
            }
            if (os != null) {
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**=========================================单元格字体样式设置 start================================================*/

    /**
     * 单元格样式,字体居中、字体加粗、宋体
     *
     * @return
     * @throws WriteException
     */
    private static WritableCellFormat getCenterBoldCellFormat(int fontSize) throws WriteException {
        // 字体样式，加粗，10
        WritableFont boldFont = new WritableFont(MY_ARIAL, fontSize, WritableFont.BOLD);
        WritableCellFormat wcfCenter = getDefaultWritableCellFormat(boldFont);
        // 文字水平居中对齐
        wcfCenter.setAlignment(Alignment.CENTRE);
        return wcfCenter;
    }

    /**
     * 单元格样式,字体居中、字体加粗、宋体、红色
     *
     * @return
     * @throws WriteException
     */
    private static WritableCellFormat getRedCenterBoldCellFormat(int fontSize) throws WriteException {
        // 字体样式，加粗，10
        WritableFont boldFont = new WritableFont(
                MY_ARIAL,                       // 宋体
                fontSize,                       // 字体大小
                WritableFont.BOLD,              // 加粗
                false,                          // 不斜体
                UnderlineStyle.NO_UNDERLINE,    //无下划线
                Colour.RED);                    //颜色-红色

        WritableCellFormat wcfCenter = getDefaultWritableCellFormat(boldFont);
        // 文字水平居中对齐
        wcfCenter.setAlignment(Alignment.CENTRE);
        return wcfCenter;
    }

    /**
     * 单元格样式：宋体，居中、不加粗
     *
     * @param fontSize 字体大小
     * @return
     * @throws WriteException
     */
    private static WritableCellFormat getCenterNoBoldCellFormat(int fontSize) throws WriteException {
        // 字体样式，
        WritableFont BoldFont = new WritableFont(MY_ARIAL, fontSize);
        WritableCellFormat wcfCenter = getDefaultWritableCellFormat(BoldFont);
        // 文字水平居左对齐
        wcfCenter.setAlignment(Alignment.CENTRE);
        return wcfCenter;
    }

    /**
     * 单元格样式：宋体，居左、不加粗
     *
     * @param fontSize 字体大小
     * @return
     * @throws WriteException
     */
    private static WritableCellFormat getLeftNoBoldCellFormat(int fontSize) throws WriteException {
        // 字体样式，
        WritableFont BoldFont = new WritableFont(MY_ARIAL, fontSize);
        WritableCellFormat wcfCenter = getDefaultWritableCellFormat(BoldFont);
        // 文字水平居左对齐
        wcfCenter.setAlignment(Alignment.LEFT);
        return wcfCenter;
    }

    /**
     * 时间类型正文
     *
     * @param fontSize
     * @return
     * @throws WriteException
     */
    private static WritableCellFormat getLeftNoBoldDateCellFormat(int fontSize) throws WriteException {
        // 字体样式，
        WritableFont boldFont = new WritableFont(MY_ARIAL, fontSize);

        // 时间格式
        DateFormat df = new DateFormat(DATE_FORMAT);
        WritableCellFormat wcfDF = new WritableCellFormat(df);

        wcfDF.setFont(boldFont);
        // 默认样式
        setDefaultWritableCellFormat(wcfDF);
        // 文字水平居左对齐
        wcfDF.setAlignment(Alignment.LEFT);
        return wcfDF;
    }


    /**
     * 单元格默认样式
     *
     * @param writableFont 单元格字体
     * @return
     * @throws WriteException
     */
    private static WritableCellFormat getDefaultWritableCellFormat(WritableFont writableFont) throws WriteException {
        // 用于正文居左
        WritableCellFormat writableCellFormat = new WritableCellFormat(writableFont);
        setDefaultWritableCellFormat(writableCellFormat);
        return writableCellFormat;
    }

    /**
     * 默认单元格样式
     *
     * @param writableCellFormat
     * @throws WriteException
     */
    private static void setDefaultWritableCellFormat(WritableCellFormat writableCellFormat) throws WriteException {
        // 线条边框-细的
        writableCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        // 文字垂直对齐
        writableCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        // 文字是否换行
        writableCellFormat.setWrap(true);
    }

    /**=========================================单元格字体样式设置 end================================================*/


    /** ========================================= 正文数据导出 start ================================================ */


    /**
     * 标题栏设置
     *
     * @param sheet
     * @param contentTitle
     * @param columnWidth
     * @throws WriteException
     */
    private static void addCellFromTitle(
            WritableSheet sheet,
            String[] contentTitle,
            int[] columnWidth,
            int startRow,
            WritableCellFormat wcfCenter) throws WriteException {
        for (int i = 0; i < contentTitle.length; i++) {
            sheet.setColumnView(i, columnWidth[i]);
            sheet.addCell(new Label(i, startRow, contentTitle[i], wcfCenter));
        }
    }

    /**
     * 循环把数据添加到excel中
     *
     * @param sheet
     * @param listContent
     * @param startRow
     * @param wcfCenter   字符串单元格样式
     * @param wcfDF       时间单元格样式
     * @throws IllegalAccessException
     * @throws WriteException
     */
    private static void loopAddCellFromListContent(
            WritableSheet sheet,
            List listContent,
            int startRow,
            int startColumn,
            WritableCellFormat wcfCenter,
            WritableCellFormat wcfDF) throws IllegalAccessException, WriteException {

        // 开始行数
        int row = startRow;
        for (Object obj : listContent) {
            Field[] fields = obj.getClass().getDeclaredFields();
            // 开始列树
            int column = startColumn;
            for (Field field : fields) {
                // 反射获取private变量值
                field.setAccessible(true);
                Object value = field.get(obj);
                // 格式化数据到单元格
                sheetAddCell(sheet, value, column, row, wcfCenter, wcfDF);
                column++;
            }
            row++;
        }

    }

    /**
     * 添加数据到excel的单元格中
     *
     * @param sheet
     * @param value
     * @param column
     * @param row
     * @param wcfCenter
     * @param wcfDF
     * @throws WriteException
     */
    private static void sheetAddCell(
            WritableSheet sheet,
            Object value,
            int column,
            int row,
            WritableCellFormat wcfCenter,
            WritableCellFormat wcfDF) throws WriteException {

        if (value == null) {
            // 字符串
            value = "";
            sheet.addCell(new Label(column, row, value.toString(), wcfCenter));
            return;
        }

        if (value instanceof String) {
            // 字符串
            sheet.addCell(new Label(column, row, value.toString(), wcfCenter));
        } else if (value instanceof Number) {
            // 数字类型
            sheet.addCell(new jxl.write.Number(column, row, ((Number) value).doubleValue(), wcfCenter));
        } else if (value instanceof Boolean) {
            // Boolean类型
            sheet.addCell(new jxl.write.Boolean(column, row, (Boolean) value, wcfCenter));
        } else if (value instanceof java.util.Date) {
            // 时间类型
            sheet.addCell(new DateTime(column, row, (java.util.Date) value, wcfDF));
        } else if (value instanceof java.sql.Date) {
            // 时间类型
            sheet.addCell(new DateTime(column, row, (java.sql.Date) value, wcfDF));
        } else {
            sheet.addCell(new Label(column, row, value.toString(), wcfCenter));
        }
    }

    /** =============================== 正文数据导出 end ================================ */

    /** ******************************* 公共方法 end ************************************/


}
