package com.starry.sky.outrun.doc;

import java.io.InputStream;

public class DocRun {

    public static void main(String[] args) {
        String path = "D:\\cmd.bat";
        Runtime run = Runtime.getRuntime();
        try {
            //run.exec("cmd /k shutdown -s -t 3600");
            Process process = run.exec("cmd.exe /k start " + path);
            InputStream in = process.getInputStream();
            while (in.read() != -1) {
                System.out.println(in.read());
            }
            in.close();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
