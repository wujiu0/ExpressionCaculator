package nuc.ss.mylist_v5.List;

import java.util.Scanner;

public class MyArrayListTest {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        MyList list = new MyArrayList();
        int flag = 1;
        while (flag == 1) {
            flag = menu(list);
        }
        sc.close();
    }

    public static int menu(MyList list) {
        System.out.println("--------------");
        System.out.println("0.退出");
        System.out.println("1.清空线性表");
        System.out.println("2.插入元素");
        System.out.println("3.删除元素");
        System.out.println("4.查找");
        System.out.println("5.获取元素");
        System.out.println("6.替换元素");
        System.out.println("7.显示线性表");
        System.out.println("8.求长度");
        System.out.println("---------------");
        System.out.println("请输入要进行的操作：");
        int tmp = sc.nextInt();
        int num;
        String str;
        MyArrayList list1 = (MyArrayList) list;
        switch (tmp) {
            case 0:
                return 0;
            case 1:
                list.clear();
                break;
            case 2:
                System.out.println("线性表当前的长度为：" + list.size());
                System.out.println("请输入插入位置：");
                num = sc.nextInt();
                System.out.println("请输入元素：");
                str = sc.next();
                list.add(num, str);
                break;
            case 3:
                System.out.println("请输入删除位置：");
                num = sc.nextInt();
                list.remove(num);
                break;
            case 4:
                System.out.println("请输入查找的元素：");
                str = sc.next();
                if (list.indexOf(str) < 0) {
                    System.out.println("线性表中不存在" + str);
                } else {
                    int result = list.indexOf(str) + 1;
                    System.out.println(str + "在线性表中的位置是：" + result);
                }
                break;
            case 5:
                System.out.println("请输入位置：");
                num = sc.nextInt();
                System.out.println("第" + num + "号元素是" + list1.get(num));
                break;
            case 6:
                System.out.println("请输入欲替换的位置：");
                num = sc.nextInt();
                System.out.println("请输入要换的值：");
                str = sc.next();
                list.set(num, str);
                break;
            case 7:
                list1.visit();
                break;
            case 8:
                System.out.println("线性表当前的长度为：" + list.size());
            default:
                System.out.println("输入出错");
                break;
        }
        return 1;
    }
}
