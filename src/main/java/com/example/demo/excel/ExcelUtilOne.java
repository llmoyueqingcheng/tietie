package com.example.demo.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @description: 多个Excel合并Sheet
 * @author: wyj
 * @time: 2020/9/18 15:28
 */
public class ExcelUtilOne {

    public static void main(String[] args) throws IOException {

        // 将所有类型的尽调excel文件合并成一个excel文件
        String Path = "/Users/mocheng/Documents/excel2";
        File file = new File(Path);
        File[] tempList = file.listFiles();
        List<String> excelList = new ArrayList<>();
        System.out.println("该目录下对象个数：" + excelList.size());
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                String filename = tempList[i].toString();
                if(filename.contains(".xlsx")){
                    excelList.add(filename);
                    System.out.println("文件:" + filename + " 待处理");
                }
            }
        }
        XSSFWorkbook newExcelCreat = new XSSFWorkbook();
        XSSFSheet newSheet = newExcelCreat.createSheet("合并");
        int point = 0;
        int countNum=0;
        for (String fromExcelName : excelList) {    // 遍历每个源excel文件，TmpList为源文件的名称集合

            InputStream in = new FileInputStream(fromExcelName);
            XSSFWorkbook fromExcel = new XSSFWorkbook(in);
            int length = fromExcel.getNumberOfSheets();
            if (length <= 1) {       //长度为1时
                XSSFSheet oldSheet = fromExcel.getSheetAt(0);
                countNum = copySheet(newExcelCreat, oldSheet, newSheet, countNum,point);
            } else {
                for (int i = 0; i < length; i++) {// 遍历每个sheet
                    XSSFSheet oldSheet = fromExcel.getSheetAt(i);
                    countNum = copySheet(newExcelCreat, oldSheet, newSheet, countNum, point);
                }
            }
            point++;
            System.out.println(fromExcelName+"执行"+point+"多少行"+countNum);
//            if(point>4){
//                break;
//            }

        }
        String allFileName = "/Users/mocheng/Documents/New3.xlsx";    //定义新生成的xlx表格文件
        FileOutputStream fileOut = new FileOutputStream(allFileName);
        newExcelCreat.write(fileOut);
        fileOut.flush();
        fileOut.close();
//		// 删除各个源文件
//		for (String fromExcelName : TmpList) {// 遍历每个源excel文件
//			File Existfile = new File(fromExcelName);
//			if (Existfile.exists()) {
//				Existfile.delete();
//			}
//		}
        System.out.println("运行结束!");

    }

    public static void copyCellStyle(XSSFCellStyle fromStyle, XSSFCellStyle toStyle) {

        // 此一行代码搞定
        toStyle.cloneStyleFrom(fromStyle);
        // 下面统统不用
        /*
         * //对齐方式 toStyle.setAlignment(fromStyle.getAlignment()); //边框和边框颜色
         * toStyle.setBorderBottom(fromStyle.getBorderBottom());
         * toStyle.setBorderLeft(fromStyle.getBorderLeft());
         * toStyle.setBorderRight(fromStyle.getBorderRight());
         * toStyle.setBorderTop(fromStyle.getBorderTop());
         * toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
         * toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
         * toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
         * toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor()); //背景和前景
         * //toStyle.setFillPattern(fromStyle.getFillPattern());
         * //填充图案，不起作用，转为黑色
         * toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
         * //不起作用
         * toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());
         * toStyle.setDataFormat(fromStyle.getDataFormat()); //数据格式
         * //toStyle.setFont(fromStyle.getFont()); //不起作用
         * toStyle.setHidden(fromStyle.getHidden());
         * toStyle.setIndention(fromStyle.getIndention());//首行缩进
         * toStyle.setLocked(fromStyle.getLocked());
         * toStyle.setRotation(fromStyle.getRotation());//旋转
         * toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
         * //垂直对齐 toStyle.setWrapText(fromStyle.getWrapText()); //文本换行
         */
    }

    /**
     * 合并单元格
     *
     * @param fromSheet
     * @param toSheet
     */
    public static void mergeSheetAllRegion(XSSFSheet fromSheet, XSSFSheet toSheet) {
        int num = fromSheet.getNumMergedRegions();
        CellRangeAddress cellR = null;
        for (int i = 0; i < num; i++) {
            cellR = fromSheet.getMergedRegion(i);
            toSheet.addMergedRegion(cellR);
        }
    }

    /**
     * 复制单元格
     * @param wb
     * @param fromCell
     * @param toCell
     * @param point
     * @param newstyle
     */
    public static void copyCell(XSSFWorkbook wb, XSSFCell fromCell, XSSFCell toCell, int point, XSSFCellStyle newstyle) {
        if (fromCell != null) {
                if (fromCell.getCellComment() != null) {
                    toCell.setCellComment(fromCell.getCellComment());
                }
                // 不同数据类型处理
                CellType fromCellType = fromCell.getCellType();
                if (fromCellType == CellType.NUMERIC) {
                    if (XSSFDateUtil.isCellDateFormatted(fromCell)) {
                        toCell.setCellValue(fromCell.getDateCellValue());
                    } else {
                        toCell.setCellValue(fromCell.getNumericCellValue());
                    }
                } else if (fromCellType == CellType.STRING) {
                    toCell.setCellValue(fromCell.getRichStringCellValue());
                } else if (fromCellType == CellType.BLANK) {
                    // nothing21
                } else if (fromCellType == CellType.BOOLEAN) {
                    toCell.setCellValue(fromCell.getBooleanCellValue());
                } else if (fromCellType == CellType.ERROR) {
                    toCell.setCellErrorValue(fromCell.getErrorCellValue());
                } else if (fromCellType == CellType.FORMULA) {
                    toCell.setCellFormula(fromCell.getCellFormula());
                } else { // nothing29
                }
        }


    }


    /**
     * 行复制功能
     *  @param wb
     * @param oldRow
     * @param toRow
     * @param point
     */
    public static void copyRow(XSSFWorkbook wb, XSSFRow oldRow, XSSFRow toRow, int point) {
        if(oldRow!=null){
        toRow.setHeight(oldRow.getHeight());
//        for (int i = 0; i <= 30; i++) {
//            XSSFCell tmpCell = (XSSFCell) oldRow.getCell(i);
//            XSSFCell newCell = toRow.createCell(i);
//            copyCell(wb, tmpCell, newCell);
//
//        }

            for (Iterator cellIt = oldRow.cellIterator(); cellIt.hasNext();) {
                XSSFCellStyle newstyle = wb.createCellStyle();
                XSSFCell tmpCell = (XSSFCell) cellIt.next();
                XSSFCell newCell = toRow.createCell(tmpCell.getColumnIndex());
                XSSFCellStyle aa = wb.createCellStyle();
                copyCellStyle(tmpCell.getCellStyle(), newstyle);
                //  toCell.setEncoding(fromCell.getStringCelllValue());
                // 样式
                newCell.setCellStyle(newstyle);
                copyCell(wb, tmpCell, newCell,point,newstyle);
            }
        }

    }

    /**
     * Sheet复制
     * @param wb
     * @param fromSheet
     * @param toSheet
     * @param countNum
     */
    public static Integer copySheet(XSSFWorkbook wb, XSSFSheet fromSheet, XSSFSheet toSheet, int countNum, int point) {
        mergeSheetAllRegion(fromSheet, toSheet);
        // 设置列宽
        int length = fromSheet.getRow(fromSheet.getFirstRowNum()).getLastCellNum();
        for (int i = 0; i <= length; i++) {
            toSheet.setColumnWidth(i, fromSheet.getColumnWidth(i));
        }
        int lastRowNum = fromSheet.getLastRowNum();
        for (int i = 0; i <=lastRowNum ; i++) {
            if(fromSheet.getRow(i)!=null){
                XSSFRow boolearnoldRow =  fromSheet.getRow(i);
                if(boolearnoldRow!=null){
                    boolean flag = false;
                    for (Iterator cellIt = boolearnoldRow.cellIterator(); cellIt.hasNext();) {
                        if(cellIt!=null){
                            Object next = cellIt.next();
                            if(next.toString()!=null&&next.toString()!="") {
                                flag = true;
                                break;
                            }
                        }
                    }
                    if(flag){
                            if (point >=1) {
                                XSSFRow oldRow = fromSheet.getRow((i+5));
                                XSSFRow newRow = toSheet.createRow(countNum);
                                countNum++;
                                copyRow(wb, oldRow, newRow, point);
                            } else {
                                XSSFRow oldRow = fromSheet.getRow(i);
                                XSSFRow newRow = toSheet.createRow(countNum);
                                countNum++;
                                copyRow(wb, oldRow, newRow, point);
                            }
                    }
                }
            }

        }
//        if(point>1){
//            int tempInt = 0;
//            for (Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext(); ) {
//                if(tempInt>=5){
//                    XSSFRow oldRow = (XSSFRow) rowIt.next();
//                    XSSFRow newRow = toSheet.createRow(countNum++);
//                    copyRow(wb, oldRow, newRow,point);
//                }else{
//                    tempInt++;
//                    continue;
//                }
//
//            }
//        }else {
//            for (Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext(); ) {
//                XSSFRow oldRow = (XSSFRow) rowIt.next();
//                XSSFRow newRow = toSheet.createRow(countNum++);
//                copyRow(wb, oldRow, newRow, point);
//            }
//        }

        return countNum;
    }

    public class XSSFDateUtil extends DateUtil {

    }

}