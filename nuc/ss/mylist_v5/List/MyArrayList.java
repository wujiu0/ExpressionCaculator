package nuc.ss.mylist_v5.List;

import java.util.Arrays;

public class MyArrayList extends MyAbstractList {
    private String[] data;
    private int capacity;

    public MyArrayList() {
        init();
    }

    public MyArrayList(int size, String[] data) {
        super(size);
        this.data = data;
    }

    public String[] getData() {
        return data;
    }

    // 初始化线性表
    public void init() {
        data = new String[10];
        capacity = data.length;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public void add(int position, String str) {
        if (position < 1) {
            System.out.println("插入位置不合法");
            return;
        }
        // 若线性表空间不足，扩容数组
        if (size >= capacity) {
            data = Arrays.copyOf(data, capacity + 10);
            capacity += 10;
        }

        if (position > size + 1) {
            System.out.println("输入位置超出当前线性表长度，自动将元素插入末尾");
            add(str);
        } else {
            int j = size;
            while (j > position - 1) {
                data[j] = data[j - 1];// 插入位置及之后的元素右移
                j--;
            }
            data[j] = str;
            size++;
        }
    }

    // 末尾插入元素
    public void add(String str) {
        add(size + 1, str);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public String remove(int position) {
        if (position < 1 || position > size) {
            System.out.println("删除位置不合法");
            return null;
        }

        int j = position - 1;
        String str = data[j];
        while (j <= size) {
            data[j] = data[j + 1];// 删除位置及之后的元素前移
            j++;
        }
        size--;
        return str;
    }

    @Override
    public int indexOf(String str) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void set(int position, String str) {
        System.out.println("结果为：");
        for (int i = 0; i < size; i++) {
            System.out.println(data[i]);
        }
    }

    // 获取元素
    public String get(int position) {
        if (position < 1 || position > size) {
            System.out.println("输入位置不合法");
            return "Error";
        }
        return data[position - 1];
    }

    // 遍历线性表
    public void visit() {
        for (int i = 0; i < size; i++) {
            System.out.println(data[i]);
        }
    }
}
