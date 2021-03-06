package com.stive;

import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 带括号的四则运算
 *
 * @author stive 2019/4/23 15:15
 */
public class Arithmetic1 {

    private LinkedList<String> optStr = new LinkedList<>();

    public int cal(String calStr) {
        parseCalStr(calStr);
        Arithmetic arithmetic = new Arithmetic();
        String s = optStr.stream().reduce(String::concat).orElseThrow(()->new RuntimeException("字符为空"));
        return arithmetic.cal(s);
    }

    private void parseCalStr(String calStr) {
        if (calStr.startsWith("-") || calStr.startsWith("=")) calStr = "0" + calStr;
        StringTokenizer tokenizer = new StringTokenizer(calStr, "()", true);
        while (tokenizer.hasMoreTokens()) {
            String s = tokenizer.nextToken();
            if (s.matches("(\\d+[+*/\\\\-]\\d+)*([+*/\\\\-]\\d+)*(\\d+[+*/\\\\-])*")) {
                optStr.addLast(s);
            }
            if (")".equals(s)) {
                String last = optStr.removeLast();
                int cal = new Arithmetic().cal(last);
                String appendStr;
                if (optStr.size() > 0) {
                    appendStr = optStr.removeLast() + cal;
                } else {
                    appendStr = String.valueOf(cal);
                }
                optStr.addLast(appendStr);
            }
        }
    }
}
