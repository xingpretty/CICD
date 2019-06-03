package xing.start.game.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class ImageHash {
    private final static Logger logger = LoggerFactory.getLogger(ImageHash.class);

    public String hashImage() {
        return hashImage(null);
//        return testDigest();
    }

    private String hashImage(String path) {
        int len;
        int bufferSize = 4096;
        if (path == null) {
            path = "D:\\cat.jpg";
            logger.debug(path);
        }
        File file = new File("D:\\cat.jpg");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            FileInputStream fileInputStream = new FileInputStream(file);
//            DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
            byte[] buffer = new byte[bufferSize];
            while ((len = fileInputStream.read(buffer, 0, 1024)) != -1) {
                messageDigest.update(buffer, 0, len);
            }
//            messageDigest = digestInputStream.getMessageDigest();
//            messageDigest.update(buffer,0,len);
            byte[] resultByteArray = messageDigest.digest();
            logger.info(byteArray2HexStr(resultByteArray));
            return byteArray2HexStr(resultByteArray);
//            messageDigest.digest(file);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("摘要算法没有找到");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件没有找到");
        } catch (IOException e) {
            throw new RuntimeException("IO出现问题");
        } finally {
            logger.debug("run finally");
        }

    }

    private String byte2hex(byte[] b) //二行制转字符串
    {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) hs.append("0").append(stmp);
            else hs.append(stmp);
            if (n < b.length - 1) hs.append(":");
        }
        return hs.toString().toUpperCase();
    }

    private static String byteArray2HexStr(byte[] bs) {
        StringBuilder md5StrBuff = new StringBuilder();
        for (byte b : bs) {
            if (Integer.toHexString(0xFF & b).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & b));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & b));
            }
        }
        return md5StrBuff.toString();
    }

    public void testDigest() {
        try {
            String myinfo = "我的测试信息";
            //java.security.MessageDigest alg=java.security.MessageDigest.getInstance("MD5");
            java.security.MessageDigest alga = java.security.MessageDigest.getInstance("SHA-1");
            alga.update(myinfo.getBytes());
            byte[] digesta = alga.digest();
            System.out.println("本信息摘要是:" + byte2hex(digesta));
            //通过某中方式传给其他人你的信息(myinfo)和摘要(digesta) 对方可以判断是否更改或传输正常
            java.security.MessageDigest algb = java.security.MessageDigest.getInstance("SHA-1");
            algb.update(myinfo.getBytes());
            if (MessageDigest.isEqual(digesta, algb.digest())) {
                System.out.println("信息检查正常");
            } else {
                System.out.println("摘要不相同");
            }
        } catch (java.security.NoSuchAlgorithmException ex) {
            System.out.println("非法摘要算法");
        }
    }
}

