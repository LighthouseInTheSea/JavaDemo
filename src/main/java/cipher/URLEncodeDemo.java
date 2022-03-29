package cipher;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author: 祁文杰(灯塔)
 * @Date: 2022/3/29 16:17
 * @Description: 如果字符是A~Z，a~z，0~9以及-、_、.、*，则保持不变；
 * 如果是其他字符，先转换为UTF-8编码，然后对每个字节以%XX表示。
 * URL编码 字符"中"的UTF-8编码是0xe4b8ad，因此，它的URL编码是%E4%B8%AD。URL编码总是大写
 */
public class URLEncodeDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("中文!", StandardCharsets.UTF_8.name());
        String decode = URLDecoder.decode(encode, StandardCharsets.UTF_8.name());

        System.out.println(decode);
    }
}