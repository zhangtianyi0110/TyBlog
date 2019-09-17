package com.ty.blog.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName: Md5Utils
 * @Description: MD5工具类
 * @author zhangtainyi
 * @date 2019/9/17 16:44
 *
 */
public class Md5Utils {

    /**
     * 算法
     */
    private static final String ALGORITH_NAME = "md5";

    /**
     * 加盐迭代次数
     */
    private static final int HASH_ITERATIONS = 1;

    public static String encrypt(String password, String salt) {
        String newPassword = new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String encrypt(String username, String password, String salt) {
        String newPassword = new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(username + salt),
                HASH_ITERATIONS).toHex();
        return newPassword;
    }
    public static void main(String[] args) {

        System.out.println(Md5Utils.encrypt("123456","admin"));
    }

}
