package com.xuxy.demo.collection.sort;

/**
 * 直接插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] a = {30,20,25,15};
        insertSort(a,a.length);
    }

    /**
     *
     * @param a 需要排序的数组
     * @param n 数组的长度
     */
    public static void insertSort(int[] a,int n){
        //声明三个临时变量
        int i,j,k;

        // 循环数组长度
        for(i = 1; i < n; i++) {
            //为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
            for(j = i-1; j >= 0; j--) {
                if(a[j]<a[i] ) {
                    break;
                }
            }
            //如找到了一个合适的位置
            if(j != i - 1) {
                int temp = a[i];
                //将比a[i]大的数据向后移
                for(k = i-1; k >j;k--) {
                    a[k+1] = a[k];
                }
                //将a[i]放到正确位置上
                a[k+1] = temp;
            }
        }

    }
}
