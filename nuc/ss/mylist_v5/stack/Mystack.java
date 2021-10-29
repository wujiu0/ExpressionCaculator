package nuc.ss.mylist_v5.stack;

import java.util.Arrays;

import nuc.ss.mylist_v5.List.MyArrayList;
import nuc.ss.mylist_v5.List.MyList;

public class Mystack {
    MyList list = new MyArrayList();

    /**
     * 获取栈的大小
     */
    public int getSize() {
        return list.size();
    }

    /**
     * 获取栈顶元素
     */
    public String peek() {
        if (this.isEmpty()) {
            return null;
        }
        String str = list.get(list.size());
        return str;
    }

    /**
     * 压栈
     */
    public void push(String str) {
        list.add(list.size() + 1, str);
    }

    /**
     * 出栈
     */
    public String pop() {
        if (this.isEmpty()) {
            return null;
        }
        String str = list.remove(list.size());
        return str;
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        MyArrayList lists = (MyArrayList) list;
        return Arrays.toString(lists.getData());
    }

    public void clear() {
        list.clear();
    }
}
