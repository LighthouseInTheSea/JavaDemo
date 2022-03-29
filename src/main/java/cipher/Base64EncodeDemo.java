package cipher;

import java.util.Arrays;
import java.util.Base64;

/**
 * @author: 祁文杰(灯塔)
 * @Date: 2022/3/29 16:28
 * @Description: Base64编码的目的是把二进制数据变成文本格式，这样在很多文本中就可以处理二进制数据。例如，电子邮件协议就是文本协议，如果要在电子邮件中添加一个二进制文件，就可以用Base64编码，然后以文本的形式传送。
 * Base64编码的缺点是传输效率会降低，因为它把原始数据的长度增加了1/3。
 */
public class Base64EncodeDemo {

    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    /**
     * 它的原理是把3字节的二进制数据按6bit一组，用4个int整数表示，然后查表，把int整数用索引对应到字符，得到编码后的字符串
     */
    public static void test1(){
        byte[] bytes = {(byte) 0xe4, (byte) 0xb8, (byte) 0xad};
        String b64 = Base64.getEncoder().encodeToString(bytes);

        byte[] output = Base64.getDecoder().decode(b64);
        System.out.println(Arrays.toString(output));
    }

    /**
     * 如果输入的byte[]数组长度不是3的整数倍肿么办？这种情况下，需要对输入的末尾补一个或两个0x00，
     * 编码后，在结尾加一个=表示补充了1个0x00，加两个=表示补充了2个0x00，解码的时候，去掉末尾补充的一个或两个0x00即可
     * 实际上，因为编码后的长度加上=总是4的倍数，所以即使不加=也可以计算出原始输入的byte[]。Base64编码的时候可以用withoutPadding()去掉=，解码出来的结果是一样的
     */
    public static void test2(){
        byte[] input = new byte[] { (byte) 0xe4, (byte) 0xb8, (byte) 0xad, 0x21 };
        //5LitIQ==
        String b64encoded = Base64.getEncoder().encodeToString(input);
        //5LitIQ
        String b64encoded2 = Base64.getEncoder().withoutPadding().encodeToString(input);
        System.out.println(b64encoded);
        System.out.println(b64encoded2);
        byte[] output = Base64.getDecoder().decode(b64encoded2);
        System.out.println(Arrays.toString(output));
    }

    /**
     * 因为标准的Base64编码会出现+、/和=，所以不适合把Base64编码后的字符串放到URL中。一种针对URL的Base64编码可以在URL中使用的Base64编码，
     * 它仅仅是把+变成-，/变成_
     */
    public static void test3(){
        byte[] input = new byte[] { 0x01, 0x02, 0x7f, 0x00 };
        String b64encoded = Base64.getUrlEncoder().encodeToString(input);
        System.out.println(b64encoded);
        byte[] output = Base64.getUrlDecoder().decode(b64encoded);
        System.out.println(Arrays.toString(output));
    }
}