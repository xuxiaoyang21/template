package com.xuxy.utils.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.xuxy.utils.Jsonable;
import com.xuxy.utils.Pageable;



import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 新分页实体类
 * Created by IntelliJ IDEA.
 * User: xuxiaoyang
 * Date: 2015-4-16
 * Time: 9:54
 * Email:chenzhe871101@126.com
 */
public class NewPagination<T> extends ArrayList<T> implements Pageable<T>, Jsonable {
    protected int pageNo;  //
    protected int pageSize;
    protected long total;

    private static final NewPagination EMPTY = new NewPagination();
    private static SerializeConfig mapping = new SerializeConfig();
    private static SerializerFeature[] features;

    public static <X> NewPagination<X> empty() {
        return EMPTY;
    }

    public NewPagination() {
        this.pageNo = 1;
        this.pageSize = 20;
        this.total = 0L;
    }

    public NewPagination(Collection<? extends T> c) {
        super(c);
        this.pageNo = 1;
        this.pageSize = 20;
        this.total = 0L;
    }

    public NewPagination(int pageNo) {
        this(pageNo, 20);
    }

    public NewPagination(int pageNo, int pageSize) {
        this.pageNo = 1;
        this.pageSize = 20;
        this.total = 0L;
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
    }

    public NewPagination(Collection<? extends T> c, long total, int pageNo, int pageSize) {
        this(c);
        this.setTotal(total);
        this.setPageNo(pageNo);
        this.setPageSize(pageSize);
    }

    public int getTotalPage() {
        if (this.total <= 0L) {
            return 0;
        } else {
            int n = (int) (this.total / (long) this.pageSize);
            return this.total % (long) this.pageSize == 0L ? n : n + 1;
        }
    }

    public boolean hasPrevPage() {
        return this.pageNo > 1;
    }

    public boolean hasNextPage() {
        return this.pageNo < this.getTotalPage();
    }

    public int getFirstPage() {
        return 1;
    }

    public int getLastPage() {
        return this.getTotalPage();
    }

    public int getPrevPage() {
        return this.hasPrevPage() ? this.pageNo - 1 : 1;
    }

    public int getNextPage() {
        return this.hasNextPage() ? this.pageNo + 1 : this.getTotalPage();
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public long getTotal() {
        return this.total;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            boolean pageNo1 = true;
        } else {
            this.pageNo = pageNo;
        }

    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 20;
        } else {
            this.pageSize = pageSize;
        }

    }

    public void setTotal(long total) {
        this.total = total;
    }


    public int getOffset() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public int getOffsetEnd() {
        return this.getOffset() + this.getPageSize();
    }

    public String toJson() {
        String list = JSON.toJSONString(this, mapping, features);
        StringBuilder builder = new StringBuilder("{");
        builder.append("\"rows\":").append(list);
        builder.append(",\"pageSize\":").append(this.getPageSize());
        builder.append(",\"pageNo\":").append(this.getPageNo());
        builder.append(",\"total\":").append(this.getTotal());
        builder.append("}");
        return builder.toString();
    }

    static {
        features = new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect};
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
        mapping.put(Timestamp.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        mapping.put(Time.class, new SimpleDateFormatSerializer("HH:mm:ss"));
        mapping.put(java.util.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

}

