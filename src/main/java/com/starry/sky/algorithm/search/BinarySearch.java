package com.starry.sky.algorithm.search;

/**
 * 二分查找法
 * @author 徐晓阳
 * @创建日期（ 2020-05-07 13:19 ）
 * @description
 */
public class BinarySearch {
    /**
     * 二分查找
     * @param args
     */
    public static void main(String[] args) {

        int[] arrays = {1,4,5,6,8,9,44,91,100};
        int key = 100;
//        int value = binarySearch(arrays,key);
//        int value = binarySearchByRecursion(arrays,0,arrays.length-1,key);
        System.out.println("普通二分查找结果的索引位置："+binarySearch(arrays,key));
        System.out.println("递归二分查找结果的索引位置："+binarySearchByRecursion(arrays,0,arrays.length-1,key));
    }

    /**
     * 递归方式实现二分查找法
     * @param arrays 有序数组 正序
     * @param key 需要查找的值
     * @return 返回的索引位置
     */
    private static int binarySearchByRecursion(int[] arrays,int start,int end, int key) {
        int mid = (end - start ) / 2 + start;
        if(arrays[mid] == key) {
            return mid;
        }
        if(start>=end) {
            return -1;
        } else if(key > arrays[mid]) {
            return binarySearchByRecursion(arrays,mid+1,end,key);
        } else if(key < arrays[mid]) {
            return binarySearchByRecursion(arrays,start,mid-1,key);
        }
        return -1;
    }

    /**
     * 二分查找
     *  @param arrays 有序数组
     *  @param key 查找的值
     * @return 数据索引值
     * */
    private static int binarySearch(int[] arrays, int key) {
        int mid;
        int start = 0;
        int end = arrays.length-1;
        while (start <= end) {
            mid = (end - start) / 2 +start;
            if(key < arrays[mid]) {
                end = mid - 1;
            } else if(key > arrays[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }




}
