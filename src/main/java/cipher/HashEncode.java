package cipher;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: 祁文杰(灯塔)
 * @Date: 2022/3/29 18:29
 * @Description: 哈希算法（Hash）又称摘要算法（Digest），它的作用是：对任意一组输入数据进行计算，得到一个固定长度的输出摘要
 * 为了避免彩虹表,可以进行加盐
 */
public class HashEncode {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //md5Test();
        sha_1Test();
    }

    /**
     * 常用的hash算法MD5,SHA-1,RipeMD-160,SHA-256,SHA-512 https://docs.oracle.com/en/java/javase/14/docs/specs/security/standard-names.html#messagedigest-algorithms
     * @throws NoSuchAlgorithmException
     */
    public static void md5Test() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update("Hello".getBytes(StandardCharsets.UTF_8.name()));
        md5.update("World".getBytes(StandardCharsets.UTF_8.name()));

        byte[] result = md5.digest();
        System.out.println(new BigInteger(1,result).toString(16));
    }

    public static void sha_1Test()throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest instance = MessageDigest.getInstance("SHA-1");
        instance.update("Hello".getBytes(StandardCharsets.UTF_8.name()));
        instance.update("World".getBytes(StandardCharsets.UTF_8.name()));

        byte[] result = instance.digest();
        System.out.println(new BigInteger(1,result).toString(20));
    }
}