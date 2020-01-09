package com.xuxy.designmode.prototype;

public  abstract class Shape implements  Cloneable {

    private String id;

    protected String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    /**
     * 绘制方法 等待实现
     */
    protected abstract void draw();

    /**
     * 实现克隆方法
     * @return
     */
    @Override
    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
