package cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 对称加密算法
 * DES,AES,IDEA
 *
 * 算法	密钥长度	    工作模式	                填充模式
 * DES	56/64	    ECB/CBC/PCBC/CTR/...	NoPadding/PKCS5Padding/...
 * AES	128/192/256	ECB/CBC/PCBC/CTR/...	NoPadding/PKCS5Padding/PKCS7Padding/...
 * IDEA	128	        ECB	                    PKCS5Padding/PKCS7Padding/...
 *
 * 密钥长度直接决定加密强度，而工作模式和填充模式可以看成是对称加密算法的参数和格式选择
 */
public class SymmetricEncryptionEncodeDemo {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        AESDemo();
    }

    /**
     * 先用ECB模式加密解密
     */
    public static void AESDemo() throws UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        // 原文
        String message = "Hello,world!";
        System.out.println("Message: " + message);

        // 128位密钥 = 16 bytes Key:
        byte[] key = "1234567890abcdef".getBytes(StandardCharsets.UTF_8.name());

        // 加密
        byte[] data = message.getBytes(StandardCharsets.UTF_8.name());
        byte[] encrypt = encrypt(key, data);
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypt));

        // 解密:
        byte[] decrypted = decrypt(key, encrypt);
        System.out.println("Decrypted: " + new String(decrypted, "UTF-8"));
    }

    /**
     * 加密
     * @param key
     * @param input
     * @return
     */
    public static byte[] encrypt(byte[] key,byte[] input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE,keySpec);
        return cipher.doFinal(input);
    }

    /**
     * 解密
     * @param key
     * @param input
     * @return
     */
    public static byte[] decrypt(byte[] key,byte[] input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE,keySpec);
        return cipher.doFinal(input);
    }
}
