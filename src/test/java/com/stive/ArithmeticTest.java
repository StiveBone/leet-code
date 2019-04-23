package com.stive;

import org.junit.Test;

/**
 * @author stive 2019/4/23 14:42
 */
public class ArithmeticTest {

    @Test
    public void test() {
        String calStr = "0*1-1";
        System.out.println(new Arithmetic().cal(calStr));
    }
}
