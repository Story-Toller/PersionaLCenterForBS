package com.yuan.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String md5(String password) {
        // 生成一个md5加密器
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
// Biginteger将8位的字符成26位的字符得到的字符形式是哈希的值
// Biginteger(参数1,参数2)参数1是1为正数  -1为负数
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
        }
        return null;
    }
}

