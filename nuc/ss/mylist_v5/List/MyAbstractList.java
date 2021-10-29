package nuc.ss.mylist_v5.List;

public abstract class MyAbstractList implements MyList {
    protected int size;// 当前线性表的长度

    public MyAbstractList() {
    }

    public MyAbstractList(int size) {
        this.size = size;
    }

    @Override
    public abstract void add(int position, String str);

    @Override
    public abstract void clear();

    @Override
    public abstract String remove(int position);

    @Override
    public abstract int indexOf(String str);

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public abstract String get(int position);

    @Override
    public abstract void set(int position, String str);

    @Override
    public int size() {
        return size;
    }

}
