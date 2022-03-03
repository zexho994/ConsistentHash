import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Zexho
 * @date 2022/3/2 10:14 PM
 */
public class Md5HashUtil implements HashUtil {

    private MessageDigest messageDigest;

    public Md5HashUtil() {
        try {
            this.messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int hash(String key) {
        this.messageDigest.update(String.valueOf(key).getBytes(StandardCharsets.UTF_8));
        byte[] digest = this.messageDigest.digest();
        int h = 0;
        for (int i = 0; i < 4; i++) {
            h |= ((int) digest[i]) & 0xFF;
            h <<= 8;
        }
        return h;
    }

}
