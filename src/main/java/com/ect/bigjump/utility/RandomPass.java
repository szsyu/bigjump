package com.ect.bigjump.utility;

import java.util.Random;

/**
 * 随机产生指定位数的密码
 */
public class RandomPass {

    static final char[] CHARS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String getRandomPassword(int passLength) {
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        int count = 0;
        int i;
        while (count < passLength) {
            //生成随机数，取绝对值，防止生成负数，

            i = Math.abs(r.nextInt(CHARS.length));  //生成的数最大为36-1

            if (i >= 0 && i < CHARS.length) {
                pwd.append(CHARS[i]);
                count++;
            }
        }
        return pwd.toString();
    }
}
