package com.easymall.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils
{
    /**
     * 使用md5的算法进行加密
     */
    public static String md5(String plainText)
    {
        byte[] secretBytes = null;
        try
        {
            // MD5 的计算结果是一个 128 位的长整数
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 把二进制转成16进制
        // 128位二进制转成16进制不足32位，需要在前面补0
        for (int i = 0; i < 32 - md5code.length(); i++)
        {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
