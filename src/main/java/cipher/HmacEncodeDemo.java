package cipher;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Hmac算法就是一种基于密钥的消息认证码算法，它的全称是Hash-based Message Authentication Code，是一种更安全的消息摘要算法。
 * Hmac算法总是和某种哈希算法配合起来用的。例如，我们使用MD5算法，对应的就是HmacMD5算法，它相当于“加盐”的MD5：
 */
public class HmacEncodeDemo {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        test1();
        test2();
    }

    /**
     * 1.通过名称HmacMD5获取KeyGenerator实例；
     * 2.通过KeyGenerator创建一个SecretKey实例；
     * 3.通过名称HmacMD5获取Mac实例；
     * 4.用SecretKey初始化Mac实例；
     * 5.对Mac实例反复调用update(byte[])输入数据；
     * 6.调用Mac实例的doFinal()获取最终的哈希值。
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
    public static void test1() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        KeyGenerator hmacMD5 = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = hmacMD5.generateKey();
        byte[] encoded = secretKey.getEncoded();
        // 打印随机生成的key
        System.out.println(new BigInteger(1,encoded).toString(16));

        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKey);
        mac.update("HelloWorld".getBytes(StandardCharsets.UTF_8.name()));
        byte[] result = mac.doFinal();
        System.out.println(new BigInteger(1,result).toString(16));
    }

    public static void test2() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        byte[] hkey = new byte[] { 106, 70, -110, 125, 39, -20, 52, 56, 85, 9, -19, -72, 52, -53, 52, -45, -6, 119, -63,
                30, 20, -83, -28, 77, 98, 109, -32, -76, 121, -106, 0, -74, -107, -114, -45, 104, -104, -8, 2, 121, 6,
                97, -18, -13, -63, -30, -125, -103, -80, -46, 113, -14, 68, 32, -46, 101, -116, -104, -81, -108, 122,
                89, -106, -109 };

        SecretKeySpec key = new SecretKeySpec(hkey, "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(key);
        mac.update("HelloWorld".getBytes(StandardCharsets.UTF_8.name()));

        byte[] result = mac.doFinal();
        System.out.println(Arrays.toString(result));
    }
}
