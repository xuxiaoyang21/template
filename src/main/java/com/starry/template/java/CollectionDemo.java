package com.starry.template.java;


import com.starry.sky.entities.User;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 集合框架demo
 *
 * @author 徐晓阳
 * @creatTime 2020-09-14 22:37
 * @description 集合测试demo
 */
public class CollectionDemo {

    public static void main(String[] args) throws IOException {


        User user = new User();
        user.setName("user1");
//        User user1 = user;
//        user1.setName("user2");
//        System.out.println();


        change(user);
        System.out.println();
//        String str = "werwe";
//        int wer = str.indexOf("wer");
//        System.out.println();
//        int[] intints = twoSum(new int[]{2,5,11,24},7);
//
//        System.out.println();
//        //位移运算
////        displacementCal();
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
//        ListNode listNode = addTwoNumbers(l1, l2);
//        System.out.println(listNode);


        //找出相差值
        lengthOfLongestSubstring("pwwkew");


        Resources.getResourceAsStream("");

    }

    private static void change(User user) {
        User user1 = user;
        user1.setName("sdafadfasdf");
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;

    }

    public static int[] twoSum(int[] nums, int target) {

        //解题 遍历数据
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {

            //计算差值
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                //包含
                int[] ints = new int[2];
                ints[0] = map.get(diff);
                ints[1] = i;
                return ints;
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //两个链表逆序相加
        //创建第三个链表

        int num1 = getValue(l1);
        int num2 = getValue(l2);
        String result = (num1 + num2) + "";
        //加入链表
        char[] chars = result.toCharArray();
        ListNode listNode = new ListNode(Integer.parseInt(chars[chars.length - 1] + ""));
        ListNode temp = listNode;
        for (int i = chars.length - 1; i > 0; i--) {
            temp.next = new ListNode(Integer.parseInt(chars[i - 1] + ""));
            temp = temp.next;
        }
        return listNode;


    }

    /**
     * 获取链表里的数据值
     *
     * @param list 集合
     * @return 返回数值
     */
    public static int getValue(ListNode list) {
        List<Integer> values = new ArrayList<>();
        values.add(list.val);
        ListNode temp = list.next;
        while (temp != null) {
            //获取第一个元素
            values.add(temp.val);
            //赋值下一个等待获取
            temp = temp.next;
        }
        StringBuilder result = new StringBuilder();
        for (int i = values.size() - 1; i >= 0; i--) {
            result.append(values.get(i));
        }

        return Integer.parseInt(result.toString());

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 位移运算测试
     *
     * @author 徐晓阳
     * @creatTime 2020-10-07 18:58
     * @version 1.0
     */
    private static void displacementCal() {
        //左移  是相当于2的n次方
        //右移  相当于除以这个数2的n次方
        int num = 4;
        num = num << 4;
        System.out.println("4左移动" + num);
        int num2 = 16;
        num2 = num2 >> 5;
        System.out.println("右移动：" + num2);
    }

}
