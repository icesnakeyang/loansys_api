package cc.cdtime.loansys_api.framework.vo;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.util.UUID;

public class GogoTools {
    /**
     * 生成一个UUID
     *
     * @return
     * @throws Exception
     */
    public static String UUID32() throws Exception {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replaceAll("-", "");
        return uuidStr;
    }

    /**
     * 对用户密码进行MD5加密
     *
     * @param password
     * @return
     * @throws Exception
     */
    public static String encoderByMd5(String password) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] b1 = md5.digest(password.getBytes("utf-8"));
        byte[] b2 = Base64.encodeBase64(b1);
        byte[] newpass = Base64.encodeBase64(md5.digest(password.getBytes("utf-8")));
        String str = new String(newpass);
        return str;

    }
}
