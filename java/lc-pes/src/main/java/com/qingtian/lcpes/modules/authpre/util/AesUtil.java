package com.qingtian.lcpes.modules.authpre.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 标题：AesUtil
 * 说明：aes ecb加解密
 * 时间：2020/2/24 18:44
 * 版权：copyright © 2019 www.tydic.com Inc. All rights reserved.
 * 注意：本内容仅限于北京天源迪科信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目的
 * <p>
 * 作者 luoyou
 */
public class AesUtil {
    private static String iv = "0123456789ABCDEF";//偏移量字符串必须是16位 当模式是CBC的时候必须设置偏移量
    private static String Algorithm = "AES";
    private static String AlgorithmProvider = "AES/ECB/PKCS5Padding"; // 算法/模式/补码方式

    static {
        Field field = null;
        try {
            field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
            field.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, Boolean.FALSE);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] generatorKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(Algorithm);
        keyGenerator.init(256);//默认128，获得无政策权限后可为192或256
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public static IvParameterSpec getIv() throws UnsupportedEncodingException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("utf-8"));
        System.out.println("偏移量：" + byteToHexString(ivParameterSpec.getIV()));
        return ivParameterSpec;
    }

    public static byte[] encrypt(String src, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        SecretKey secretKey = new SecretKeySpec(key, Algorithm);
        //IvParameterSpec ivParameterSpec = getIv();
        Cipher cipher = Cipher.getInstance(AlgorithmProvider);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherBytes = cipher.doFinal(src.getBytes(Charset.forName("utf-8")));
        return cipherBytes;
    }

    public static byte[] decrypt(String src, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, Algorithm);

        //IvParameterSpec ivParameterSpec = getIv();
        Cipher cipher = Cipher.getInstance(AlgorithmProvider);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] hexBytes = hexStringToBytes(src);
        byte[] plainBytes = cipher.doFinal(hexBytes);
        return plainBytes;
    }

    /**
     * 将byte转换为16进制字符串
     *
     * @param src
     * @return
     */
    public static String byteToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xff;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append("0");
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串装换为byte数组
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            b[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return b;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String encryptStr(String strCode, String key) {
        try {
            byte[] keyByte = key.getBytes("utf-8");
            return byteToHexString(encrypt(strCode, keyByte));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }


    public static String decryptStr(String strCode, String key) {
        try {
            byte[] keyByte = key.getBytes("utf-8");
            return new String(decrypt(strCode, keyByte), "utf-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 正则表达式验证密码
     *
     * @param input
     * @return
     */

    public static boolean rexCheckPassword(String input) {
        //1、密码长度不能少于10个字符；
        //2、密码复杂度设置必须用大小写字母、数字和特殊符号等至少3种混排；
        String regStr = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\W_]).{10,20}$";
        return input.matches(regStr);

    }


    public static void main(String[] args) {
        //String strCode = "Qsc019up";
        String strCode = "Aa123456";
        String key = "12345678901234561234567890123456";
        try {
            String ec = encryptStr(strCode, key);
            String dc = decryptStr(ec, key);
            //boolean check = rexCheckPassword(dc);
            System.out.println("tmp加密：" + ec);
            System.out.println("tmp解密：" + dc);
            //System.out.println("密码合法性验证：" + check);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
