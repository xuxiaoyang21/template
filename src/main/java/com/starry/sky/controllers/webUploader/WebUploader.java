package com.starry.sky.controllers.webUploader;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IDEA.
 * Copyright(c) SUPCON 2003-2020. 浙江浙大中控信息技术有限公司
 * author:xuxiaoyang.
 * date:2019/3/4.
 * time:9:57.
 * Description: 百度文件上传测试组件
 */
@Controller
@RequestMapping("webUploader")
public class WebUploader {

    @RequestMapping("uploader")
    public String uploaderList() {
        return "/webUploader/uploader";
    }

    @RequestMapping(value = "uploader",method = RequestMethod.POST)
    @ResponseBody
    public String uploader(HttpServletRequest request,MultipartHttpServletRequest  multipartHttpServletRequest) throws IOException {

        //获取名称
        InputStream inputStream = null;
        try {
            //获取数据流数据
            inputStream = multipartHttpServletRequest.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }

        MultipartHttpServletRequest servletRequest = (MultipartHttpServletRequest) request;
        //获取后缀名
        String lastName = servletRequest.getFile("file").getOriginalFilename();
        String nnn = lastName.split("\\.")[1];
        servletRequest.getFile("file").getInputStream();
        if("xlsx".equals(nnn)) {
            return readXLSX( new XSSFWorkbook(servletRequest.getFile("file").getInputStream()));
        } else {
            return readXLS( new HSSFWorkbook(servletRequest.getFile("file").getInputStream()));
        }

    }

    private String readXLSX(XSSFWorkbook workbook) {
        //获取行
        XSSFSheet sheet = workbook.getSheetAt(0); //获取第一个工作表
        //判断第一行是否是应该数据数据
        if(sheet != null) {
            XSSFRow row = sheet.getRow(0);
            System.out.println(row);
        }
        int success = 0; //成功标记
        int error = 0; //失败标记
        for(int i = 1; i < sheet.getLastRowNum();i++) {

            //获取当前行
            XSSFRow row = sheet.getRow(i);
            //得到第一行第一列的数据并打印
//            HSSFCell cell = row.getCell(0);
//            System.out.println(getStringValue(cell));
//            System.out.println(cell.getStringCellValue());
            try {
                System.out.println(getStringValueXLSX(row.getCell(0)));
                System.out.println(getStringValueXLSX(row.getCell(1)));
                System.out.println(getStringValueXLSX(row.getCell(2)));
                System.out.println(getStringValueXLSX(row.getCell(3)));
                System.out.println(getStringValueXLSX(row.getCell(4)));
                System.out.println(getStringValueXLSX(row.getCell(5)));

                //数据插入 代码

                success++;
            } catch (Exception e) {
                error++;
                e.printStackTrace();
                System.out.println("第"+i+"行解析错误");

            }
        }

        System.out.println("导入成功"+success+"条！");
        System.out.println("导入失败"+error+"条！");



        return "\"导入成功\"+success+\"条！\"\"导入失败\"+error+\"条！\"" ;
    }

    private String  readXLS( HSSFWorkbook workbook) {
        //获取行
        HSSFSheet sheet = workbook.getSheetAt(0); //获取第一个工作表
        //判断第一行是否是应该数据数据
        if(sheet != null) {
            HSSFRow row = sheet.getRow(0);
            System.out.println(row);
        }
        int success = 0; //成功标记
        int error = 0; //失败标记
        for(int i = 1; i < sheet.getLastRowNum();i++) {

            //获取当前行
            HSSFRow row = sheet.getRow(i);
            //得到第一行第一列的数据并打印
//            HSSFCell cell = row.getCell(0);
//            System.out.println(getStringValue(cell));
//            System.out.println(cell.getStringCellValue());
            try {
                System.out.println(getStringValue(row.getCell(0)));
                System.out.println(getStringValue(row.getCell(1)));
                System.out.println(getStringValue(row.getCell(2)));
                System.out.println(getStringValue(row.getCell(3)));
                System.out.println(getStringValue(row.getCell(4)));
                System.out.println(getStringValue(row.getCell(5)));

                //数据插入 代码

                success++;
            } catch (Exception e) {
                error++;
                e.printStackTrace();
                System.out.println("第"+i+"行解析错误");

            }
        }

        System.out.println("导入成功"+success+"条！");
        System.out.println("导入失败"+error+"条！");



        return "\"导入成功\"+success+\"条！\"\"导入失败\"+error+\"条！\"" ;
    }

    //返回string文本值
    private String getStringValue(HSSFCell cell) {
        //判断表格值数据的类型
        //如果是需要的类型直接获取值返回
        //如果不是改变类型返回
        if(cell != null) {
            if(cell.getCellTypeEnum() != CellType.STRING) {
                cell.setCellType(CellType.STRING);
            }
            return cell.getStringCellValue();
        }
        return null;
    }

    //返回string文本值
    private String getStringValueXLSX(XSSFCell cell) {
        //判断表格值数据的类型
        //如果是需要的类型直接获取值返回
        //如果不是改变类型返回
        if(cell != null) {
            if(cell.getCellTypeEnum() != CellType.STRING) {
                cell.setCellType(CellType.STRING);
            }
            return cell.getStringCellValue();
        }
        return null;
    }

    //返回double文本值
    private double getNumberValue(HSSFCell cell) {
        //判断表格值数据的类型
        //如果是需要的类型直接获取值返回
        //如果不是改变类型返回
        if(cell.getCellTypeEnum() != CellType.NUMERIC) {
            cell.setCellType(CellType.NUMERIC);
        }
        return cell.getNumericCellValue();
    }


}
