package nuc.ss.mylist_v5.Expressioncalculating;

import java.util.Scanner;

import nuc.ss.mylist_v5.stack.Mystack;

public class ExpressionCaculator {
    /**
     * 操作数栈
     */
    Mystack opnd = new Mystack();

    /**
     * 操作符栈
     */
    Mystack optr = new Mystack();

    public double EvaluateExpression() {
        String regInt = "-?[1-9]\\d*|0";// 匹配整数
        String regFloat = "-?[0-9][0-9]*[.][0-9]+";// 匹配浮点数
        String regPunct = "\\p{Punct}";// 匹配符号
        
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入表达式，每个数字和运算符之间使用空格隔开");
        String str = sc.nextLine() + " #";
        
        optr.push("#");
        String[] strs = str.split(" ");

        int i = 0;
        String stro = strs[i];
        while (!stro.equals("#") || !optr.peek().equals("#")) {
            // System.out.println("stro=" + stro + ",peek=" + optr.peek());
            // System.out.println("optr:" + optr);
            // System.out.println("opnd:" + opnd);

            // 不是运算符则进栈
            if (stro.matches(regInt) || stro.matches(regFloat)) {
                opnd.push(stro);
                stro = strs[++i];
            } else if (stro.matches(regPunct)) {

                if (!(stro.equals("+") ||stro.equals("-") || stro.equals("*") || stro.equals("/") || stro.equals("(") ||stro.equals(")"))) {
                    if(!(stro.equals("#") && i == strs.length - 1))
                    throw new ExpressionException("请输入正确的操作符（+，-，*，/）");
                }

                switch (precede(optr.peek().charAt(0), strs[i].charAt(0))) {
                case '<':
                    optr.push(stro);
                    stro = strs[++i];
                    break;
                case '=':       //脱括号并接收下一元素
                    optr.pop();
                    stro = strs[++i];
                    break;
                case '>':       //退栈并将运算入栈
                    double num1,num2;
                    char op = optr.pop().charAt(0);
                    try {
                         num2 = Double.parseDouble(opnd.pop());
                         num1 = Double.parseDouble(opnd.pop());
                        } catch (NumberFormatException e) {
                            throw new ExpressionException("操作数非法");
                        } 
                    if (op == '/' && num2 == 0 ) {
                        throw new ExpressionException("错误，0不能做除数");
                    }
                        String result = "" + Operate(num1, op, num2);
                        opnd.push(result);
                    break;
                }
            } else {
                throw new ExpressionException("输入错误，请勿输入除数字和运算符以外的其他符号，且每个数字和运算符之间使用空格隔开");
            }
        }
        if(opnd.getSize() != 1 || optr.getSize() != 1){
            throw new ExpressionException("请检查表达式是否正确");
        }
        return Double.parseDouble(opnd.peek());
    }

    public double work() {
        char flag = 'y';
        double result;
        Scanner sc = new Scanner(System.in);
        while (flag == 'y') {
            try {
                result = EvaluateExpression();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            } finally {
                opnd.clear();
                optr.clear();
            }
            System.out.println("结果是:" + result);
            System.out.println("是否继续计算？(y/n)");
            flag = sc.next().charAt(0);
        }
        return 0;
    }
    private double Operate(double num1, char op, double num2) {
        double result = 0;
        switch (op) {
        case '+':
            result = num1 + num2;
            break;
        case '-':
            result = num1 - num2;
            break;
        case '*':
            result = num1 * num2;
            break;
        case '/':
            result = num1 / num2;
            break;
        }
        return result;
    }

    /**
     * 运算符优先级比较
     *      +   -   *   /   (   )   # 
     * +    >   >   <   <   <   >   >
     * -    >   >   <   <   <   >   >
     * *    >   >   >   >   <   >   >
     * /    >   >   >   >   <   >   >
     * (    <   <   <   <   <   =
     * )    >   >   >   >       >   >
     * #    <   <   <   <   <       =
     * 
     */
    private char precede(char ch1, char ch2) {
        char ch = '=';
        switch (ch1) {
        case '+':
            switch (ch2) {
            case '+':
                ch = '>';
                break;
            case '-':
                ch = '>';
                break;
            case '*':
                ch = '<';
                break;
            case '/':
                ch = '<';
                break;
            case '(':
                ch = '<';
                break;
            case ')':
                ch = '>';
                break;
            case '#':
                ch = '>';
                break;
            }
            break;
        case '-':
            switch (ch2) {
            case '+':
                ch = '>';
                break;
            case '-':
                ch = '>';
                break;
            case '*':
                ch = '<';
                break;
            case '/':
                ch = '<';
                break;
            case '(':
                ch = '<';
                break;
            case ')':
                ch = '>';
                break;
            case '#':
                ch = '>';
                break;
            }
            break;
        case '*':
            switch (ch2) {
            case '+':
                ch = '>';
                break;
            case '-':
                ch = '>';
                break;
            case '*':
                ch = '>';
                break;
            case '/':
                ch = '>';
                break;
            case '(':
                ch = '<';
                break;
            case ')':
                ch = '>';
                break;
            case '#':
                ch = '>';
                break;
            }
            break;
        case '/':
            switch (ch2) {
            case '+':
                ch = '>';
                break;
            case '-':
                ch = '>';
                break;
            case '*':
                ch = '>';
                break;
            case '/':
                ch = '>';
                break;
            case '(':
                ch = '<';
                break;
            case ')':
                ch = '>';
                break;
            case '#':
                ch = '>';
                break;
            }
            break;
        case '(':
            switch (ch2) {
            case '+':
                ch = '<';
                break;
            case '-':
                ch = '<';
                break;
            case '*':
                ch = '<';
                break;
            case '/':
                ch = '<';
                break;
            case '(':
                ch = '<';
                break;
            case ')':
                ch = '=';
                break;
            }
            break;
        case ')':
            switch (ch2) {
            case '+':
                ch = '>';
                break;
            case '-':
                ch = '>';
                break;
            case '*':
                ch = '>';
                break;
            case '/':
                ch = '>';
                break;

            case ')':
                ch = '>';
                break;
            case '#':
                ch = '=';
                break;
            }
            break;
        case '#':
            switch (ch2) {
            case '+':
                ch = '<';
                break;
            case '-':
                ch = '<';
                break;
            case '*':
                ch = '<';
                break;
            case '/':
                ch = '<';
                break;
            case '(':
                ch = '<';
                break;
            case '#':
                ch = '=';
                break;
            }
        }
        // System.out.println(ch);
        return ch;
    }
}
