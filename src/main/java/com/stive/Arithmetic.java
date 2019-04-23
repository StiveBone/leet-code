package com.stive;

import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 四则运算 + - * /
 *
 * @author stive 2019/4/23 14:40
 */
public class Arithmetic {

    private LinkedList<Integer> optNumsStack = new LinkedList<>(); //操作数栈
    private LinkedList<String> optSymbolStack = new LinkedList<>(); //操作符栈

    public int cal(String calStr) {
        if (calStr == null || "".equals(calStr)) throw new RuntimeException("非法表达式");
        calStr = calStr.replaceAll("[\\s\\n\\t\\r]", "");
        boolean matches = calStr.matches("(\\d+[+*/\\\\-]\\d+)*([+*/\\\\-]\\d+)*");
        if (!matches) throw new RuntimeException(String.format("非法表达式%s", calStr));
        if (calStr.startsWith("-") || calStr.startsWith("+")) calStr = "0" + calStr;
        parseString(calStr);
        return calRemain();
    }

    private void parseString(String calStr) {
        StringTokenizer tokenizer = new StringTokenizer(calStr, "+-*/", true);
        boolean caling = false; //是否在下次进行计算
        while (tokenizer.hasMoreTokens()) {
            String s = tokenizer.nextToken();
            if (s.matches("\\d+")) {
                optNumsStack.addLast(Integer.valueOf(s));
                if (caling) { //首先计算乘除并入栈
                    int b = optNumsStack.removeLast();
                    int a = optNumsStack.removeLast();
                    String opt = optSymbolStack.removeLast();
                    optNumsStack.addLast(cal(a, b, opt));
                }
            } else {
                if (s.matches("[*/]")) {
                    caling = true; // * /时下次需要进行计算
                }
                optSymbolStack.addLast(s);
            }
        }
    }

    private int cal(int a, int b, String opt) {
        if ("+".equals(opt)) {
            return a + b;
        }
        if ("-".equals(opt)) {
            return a - b;
        }
        if ("*".equals(opt)) {
            return a * b;
        }
        if ("/".equals(opt)) {
            return a / b;
        }
        return 0;
    }

    private int calRemain() {
        while (optSymbolStack.size() > 0) {
            int a = optNumsStack.removeFirst();
            int b = optNumsStack.removeFirst();
            String opt = optSymbolStack.removeFirst();
            optNumsStack.addFirst(cal(a, b, opt));
        }
        return optNumsStack.removeFirst();
    }
}
