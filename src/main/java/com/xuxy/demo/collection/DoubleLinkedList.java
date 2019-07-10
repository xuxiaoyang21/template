package com.xuxy.demo.collection;

/**
 * 双向链表实现
 */
public class DoubleLinkedList<T> {


    private Node<T> node;

    private int count; //链表的长度

    //获取链表的长度
    public int size() {
        return count;
    }
    //双向链表的构造函数
    DoubleLinkedList() {
        //初始化 node
        node = new Node<>(null,null,null);
        node.prev = node.next = node;

        //初始化个数
        count = 0;
    }
    /**
     * 内部类
     * 用来构造双向链表存储数据
     * @param <T>
     */
    private class Node<T> {
        Node<T> prev;//前一个节点
        Node<T> next;//下一个节点
        T value;     // 数据值

        //构造函数
        public Node(Node prev,T value,Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }


    // 获取索引位置的元素
    public Node<T> get(int index) {
        //判断是否越界
        if(index <0 || index > count) {
            throw  new IndexOutOfBoundsException();
        }

        Node<T> tNode = node; //获取初始化节点 遍历
        for(int i = 0 ; i < index ; i++) {
            tNode = tNode.next;
        }
        return tNode;
    }

    // index位置处插入元素
    public void add(int index,T value) {
        throwException(index);
        insert(index,value);
    }

    //下标越界
    private void throwException(int index) {
        if(index<0 || index > count) {
            throw  new IndexOutOfBoundsException();
        }
    }

    //插入元素
    public void add(T value) {
        insert(count-1,value);
    }

    //插入到首节点
    public void addFirst(T value) {
        insert(0,value);
    }



    private void insert(int index,T value) {
        //首次插入元素
        if(index == 0) {
            Node<T> tNode = new Node<>(node,value,node.next);
            node.next.prev = tNode;//设置设置原先 下一个节点的上一个节点为新插入值
            node.next = tNode;//设置首节点的下一个为新插入的值
            count++;
        } else {  //之后插入
            //获取到index 节点
            Node<T> indexNode = get(index);
            //创建新的类
            Node<T> newNode = new Node<>(indexNode.prev,value,indexNode);
            indexNode.prev.next = newNode; //将当前节点的上一个节点的下指针指向 新的node
            indexNode.prev = newNode;      //将当前节点的前指针指向新的node
            count++;
        }
    }


}
