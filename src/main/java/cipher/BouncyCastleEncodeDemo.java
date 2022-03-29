package cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Security;

/**
 * BouncyCastle就是一个提供了很多哈希算法和加密算法的第三方库。它提供了Java标准库没有的一些算法，
 * 例如，RipeMD160哈希算法
 */
public class BouncyCastleEncodeDemo {

    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception {
        // 注册BouncyCastle
        Security.addProvider(new BouncyCastleProvider());

        //按名称正常调用
        MessageDigest ripeMD160 = MessageDigest.getInstance("RipeMD160");
        ripeMD160.update("HelloWorld".getBytes(StandardCharsets.UTF_8.name()));

        byte[] digest = ripeMD160.digest();
        System.out.println(new BigInteger(1,digest).toString(16));
    }
}
