package com.accp.util;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class TokenProcessor{//令牌
    /**
     * 1.构造方法私有
     * 2.自己构造一个
     * 3.对外暴露一个方法，允许获取上面创建的对象
     *
     */
    TokenProcessor(){};
    private static final TokenProcessor instance=new TokenProcessor();
    public static TokenProcessor getInstance(){
        return instance;
    }
    public String generateToken(){
        String token = System.currentTimeMillis()+new Random().nextInt()+"";//这里产生的随机数的长短不一样
        try {
            MessageDigest md=MessageDigest.getInstance("md5");//通过md5算法，得到数据指纹，数据指纹大小是一样的
            byte[] md5=md.digest(token.getBytes());
            //base64--->通过这个算法把字节转化成范围（0--63）键盘可见的字符（二进制的三个字节转化成四个字节）
            BASE64Encoder encoder=new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}