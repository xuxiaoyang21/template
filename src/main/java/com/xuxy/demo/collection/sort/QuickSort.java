package com.xuxy.demo.collection.sort;

/**
 * 快速排序
 */
public class QuickSort {


    /**
     * 从小到大排列
     * @param a  需要排序的数组
     * @param left  左边界
     * @param right 右边界
     * @return
     */
    public static int[] quickSort(int[] a, int left,int right) {

        if(left < right) {

            //选择一个基数
            int index = a[left]; //一般选取数组的第一个值为基数

            int i,j,k;

            i = left;  //从左边界开始循环 找大于基数的值
            j = right; //从右边界循环 找小于基数的值

            while(i < j) {  //当 j 和 i 相遇是结束循环  相遇位置就是基数现在应该在的位置
                //右侧循环  找小于基数的值  放到基数的位置
                while( i < j  &&  a[j] > index) {
                    j--;
                }
                if(i < j) { //当前基数的值设置为 给改值
                    a[i++] = a[j];
                }
                //左侧循环 找出大于基数的值
                while(i < j && a[i] < index) {
                    i++;
                }
                if(i<j) {
                    a[j--] = a[i]; //
                }
            }
            //基数赋值位置
            a[j] = index;

            //递归分类排序左右侧
            quickSort(a,left,i-1);
            quickSort(a,i+1,right);

        }



        return  a;
    }


    public static void main(String[] args) {
        int[] a = {10,34,52,5,23,43,52,11};
        a = quickSort(a,0,7);

        for(int c :a) {
            System.out.print(c+"--");
        }
    }
}
