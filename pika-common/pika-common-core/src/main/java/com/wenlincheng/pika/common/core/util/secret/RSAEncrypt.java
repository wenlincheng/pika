package com.wenlincheng.pika.common.core.util.secret;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * RSA密钥对工具
 *
 * @author  wenlincheng
 * @date    2020/06/13 12:39 下午
 * @version 1.0
 */
public class RSAEncrypt {
    /**
     * 用于封装随机产生的公钥与私钥
     */
    private static Map<Integer, String> keyMap = new HashMap<>();
    public static void main(String[] args) throws Exception {
        //  生成公钥和私钥
        // genKeyPair();
        //  加密字符串
        keyMap.put(0, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDldC+QHSOxhVAGtnDfKWw/r8pWEMv3oFechboM+Rz8n77/b9Gg7alcdM4PhDIiHHPiHGrRdL0vyfaFG4Vgl97N8BgwKcii/iaXqW/vHakjePPFDdl0BORoYURQsWavz9t6LOLIkdOrCsuPrhBbhPKBoMJINQSKg4v5KWSrVcKvHwIDAQAB");
        keyMap.put(1, "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOV0L5AdI7GFUAa2cN8pbD+vylYQy/egV5yFugz5HPyfvv9v0aDtqVx0zg+EMiIcc+IcatF0vS/J9oUbhWCX3s3wGDApyKL+Jpepb+8dqSN488UN2XQE5GhhRFCxZq/P23os4siR06sKy4+uEFuE8oGgwkg1BIqDi/kpZKtVwq8fAgMBAAECgYEAkxqJ47rKx2TQiX0tn+TOoYXyEntySK9je9XAEdEj5FIGAX2Ox72Wds/ry8bU3UmggbX2RZKyN5EjmvKp7+Nt0yL0dMiQiYBpoIUvKhU5bNq+CNrKvnAai60FAy2GyypceUWtR1n9KFfqwRGHS3F1cNr42PksFhgIgUdxH9boFQECQQD0GEFZv7ZrDf6cR0ySqk9TbMIKpOCBgmPEadizyHCgJEUkimuE0aGz6MCoq3CsiCpqyoKBzZ8qzR679eJRRvwVAkEA8KUgKLcexkHtMIxeRonU/6Le/HT1a9V3y72Dk33s/4K8uI+oEj6osv3z5MhfSeJuHh869tbmQJPODAdnrUgnYwJAXc5cfwNANZAhQynbcZnl9mebgr+uT9cuRDNxRlZHgiOuNsbDXz246Q0PzUJlgMirlHLTCNNe0fIWV6G9IgMnqQJAK5A4+iXsVEerAJbomixper2pUFp780F5plBweUralIa+U0ruSh89uuluNYRwe5eaB6nvqq/MjvQy4MqWYjAlsQJAWuM5s4pU8EEfjdZYt7F260G4V0GuqvNATYj2cdLeg/+EBbmwPT25HzURxnbOtoZJUSfZxoR5wNVOP2lNVw/a2w==");
        String message = "{\"phone\":\"15267153010\"}";
        String messageEn = encrypt(message,keyMap.get(0));
        System.out.println(message + "\t加密后的字符串为:" + messageEn);
        String messageDe = decrypt(messageEn,keyMap.get(1));
        System.out.println("还原后的字符串为:" + messageDe);
    }

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        // 0表示公钥
        keyMap.put(0,publicKeyString);
        // 1表示私钥
        keyMap.put(1,privateKeyString);
    }
    /**
     * RSA公钥加密
     *
     * @param str 加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt( String str, String publicKey ) throws Exception{
        // base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        // RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * RSA私钥解密
     *
     * @param str 加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        // 64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        // base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        // RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

}

