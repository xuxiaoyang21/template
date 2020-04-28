package com.starry.sky.demo.collection.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {


    /**
     * 数值有小到大排序
     * @param a
     * @param n
     */
    public static int[] bubbleSort(int[] a,int n) {

        int i,j;
        //外层需要循环的次数
        for(i = n-1; i > 0;i--) {  //外层循环一层  确定一个数值的排序

            //内层循环 少于外层的循环次数
            for(j = 0; j<i;j++) {
                if(a[j] > a[j+1]) {
                    //交换顺序
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }

        }

        return a;
    }


    public static void main(String[] args) {
        int[] a = {1,4,7,3,8,2,5,0};

        int[] b = bubbleSort(a,a.length);
        //遍历打印排完序的数组
        for(int c : b) {
            System.out.print(c+"--");
        }
    }
}
