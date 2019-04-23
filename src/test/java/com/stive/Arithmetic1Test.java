package com.stive;

import org.junit.Test;

/**
 * @author stive 2019/4/23 16:50
 */
public class Arithmetic1Test {

    @Test
    public void test() {
        String calStr = "-(1+1)*2-54+54";
        System.out.println(new Arithmetic1().cal(calStr));
    }

}
