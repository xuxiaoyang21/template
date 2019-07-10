package com.xuxy.controllers;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;

/**
 * Created with IDEA.
 * Copyright(c) SUPCON 2003-2020. 浙江浙大中控信息技术有限公司
 * author:xuxiaoyang.
 * date:2018/6/21.
 * time:16:56.
 * Description: 导出 功能控制器
 */
public class ExportController {


    //excel表格导出

    /**
     * excel表格导出
     * @return
     */
    @RequestMapping("exportExcel")
    public String exportExcel() throws IOException {

        //创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建sheet并设置名称
        HSSFSheet sheet = workbook.getSheet("页面1");

        //创建行  规定第几行  这里创建第二行
        HSSFRow hssfRow = sheet.getRow(1);
        //创建单元格 单元格属于行
        HSSFCell cell = hssfRow.getCell(0);
        cell.setCellValue("第二行第一个单元格数据填充");


        //写入到文件中
        workbook.write(new File("d:\\poi\\测试.xls"));

        workbook.close();
        return "";
    }


    public static void main(String[] args) throws IOException {
        //03版的实例
        //创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();

        //第一个sheet页面
        //创建sheet并设置名称
        HSSFSheet sheet = workbook.createSheet("页面1");

        //创建行  规定第几行  这里创建第二行
        HSSFRow hssfRow = sheet.createRow(1);
        //创建单元格 单元格属于行
        HSSFCell cell = hssfRow.createCell(0);
        cell.setCellValue("第二行第一个单元格数据填充");


        //第二个sheet页面
        HSSFSheet sheet1 = workbook.createSheet("页面2");
        HSSFRow hssfRow1 = sheet1.createRow(2);
        HSSFCell cell1 = hssfRow1.createCell(2);
        cell1.setCellValue("测啊的发生发达史蒂夫");

        //写入到文件中
        workbook.write(new File("d:\\测试.xls"));

        workbook.close();
    }
}
