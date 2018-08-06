package com.xuxy.entities;

import com.xuxy.utils.ExcelExport;

public class EsResult {



    private String name ;
    @ExcelExport.ExcelAnnotation(exportName = "经过地点")
    private String kkid;

    private String kkidStr;
    @ExcelExport.ExcelAnnotation(exportName = "经过时间")
    private String jgsj;//经过时间

    @ExcelExport.ExcelAnnotation(exportName = "号牌种类")
    private String hpzl;

    private String hphm;
    @ExcelExport.ExcelAnnotation(exportName = "总数")
    private long count;
    @ExcelExport.ExcelAnnotation(exportName = "方向")
    private String fxbh;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKkid() {
        return kkid;
    }

    public void setKkid(String kkid) {
        this.kkid = kkid;
    }

    public String getKkidStr() {
        return kkidStr;
    }

    public void setKkidStr(String kkidStr) {
        this.kkidStr = kkidStr;
    }

    public String getJgsj() {
        return jgsj;
    }

    public void setJgsj(String jgsj) {
        this.jgsj = jgsj;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getFxbh() {
        return fxbh;
    }

    public void setFxbh(String fxbh) {
        this.fxbh = fxbh;
    }
}
