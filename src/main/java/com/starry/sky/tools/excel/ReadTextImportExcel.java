package com.starry.sky.tools.excel;

import java.io.*;

/**
 * 读取文本文件 导出成excel文件数据
 */
public class ReadTextImportExcel {



    public static void main(String[] args) {

    }


    private static void readText() {
        InputStreamReader inputStreamReader = null;

        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(new File("D://text.txt")));




            inputStreamReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStreamReader !=null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
