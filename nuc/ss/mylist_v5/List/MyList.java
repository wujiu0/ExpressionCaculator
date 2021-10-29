package nuc.ss.mylist_v5.List;

public interface MyList {

    // 插入
    void add(int position, String str);

    // 查找
    int indexOf(String str);

    // 删除
    String remove(int position);

    // 获取元素
    String get(int position);

    // 替换
    void set(int position, String str);

    // 获取长度
    int size();

    // 判空
    boolean isEmpty();

    // 清空
    void clear();
}
