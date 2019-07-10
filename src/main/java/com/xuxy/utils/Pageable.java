//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xuxy.utils;

import java.util.List;

public interface Pageable<T> extends List<T> {
    int DEFAULT_PAGE_NO = 1;
    int DEFAULT_PAGE_SIZE = 20;
    long DEFAULT_TOTAL = 0L;
    int MAX_PAGE_SIZE = 1000;

    void setTotal(long var1); //设置数据条数

    void setPageNo(int var1); //设置开始页码

    void setPageSize(int var1); //设置每页的尺寸

    int getPageNo();

    int getPageSize();

    long getTotal();

    int getTotalPage(); //得到总页数

    boolean hasPrevPage();  //是否有上一页

    boolean hasNextPage(); //是否有下一页

    int getFirstPage();  //得到第一页

    int getLastPage(); //得到最后一页

    int getPrevPage(); //得到上一页

    int getNextPage(); //得到下一页

    int getOffset(); //

    int getOffsetEnd();
}
