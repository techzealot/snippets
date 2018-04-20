package net.invt.docxy;
import org.junit.Before;
import org.junit.Test;

import java.security.Key;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lake on 17-4-12.
 */
public class RSACoderTest {
    private String publicKey;
    private String privateKey;

    @Before
    public void setUp() throws Exception {
        Map<String, Key> keyMap = RSACoder.initKey();
        publicKey = RSACoder.getPublicKey(keyMap);
        privateKey = RSACoder.getPrivateKey(keyMap);
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);
    }

    @Test
    public void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String inputStr = "abc";
        byte[] encodedData = RSACoder.encryptByPublicKey(inputStr, publicKey);
        byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
                privateKey);
        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);
    }

    @Test
    public void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String inputStr = "sign";
        byte[] data = inputStr.getBytes();
        byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);
        byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);
        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);
        System.err.println("私钥签名——公钥验证签名");
        // 产生签名
        String sign = RSACoder.sign(encodedData, privateKey);
        System.err.println("签名:" + sign);
        // 验证签名
        boolean status = RSACoder.verify(encodedData, publicKey, sign);
        System.err.println("状态:" + status);
        assertTrue(status);
    }
    
    public static void main(String[] args) throws Exception {
    	String privateKey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJv79ShoZyAT9YW8OERrHKlz53pDqelyiO1ilgV7EhBgcH05pYeQp7Z9HyXok56JBiVrkbXhShK9p+OGvXhsQx5DRbpMmoVL1wQwoeKMyWrWFAReZlurOrYTpMkY9Hlr4vE05vQtvudzm5Hy3B3aTet/c3w3GNg3cMXy6bwvpMazAgMBAAECgYEAh6zENP16laBGsqhgmMJCD/JMqUApjnCdQT27F9FQiQYZUhLOM/hSqJx0d1iw7fhpbCsAdp8a+uoUGgeczh380f1z4CbqSqvqzTjRTS6VoNI+RhttvafFNm4U/eorKfRSq/ww+EYl7Q8cm2nKMNfU3CrVJB8I3OAGeDAadlemOuECQQDVkeonSiyfonxiybHj/hzYHypOU9JKf6iT1driMvdD0SoZTWSgwUCXquvUNoOt9bzo7kD7riHjnsBnJ3MBYCxfAkEAuvlA2upXdY2bbA6XzqGGHG1HT2CDENn1Usaq0WsH5fFDHbKCmliNKp0EStklZoDMPQUHjMUR3b+N17KFUEVGLQJBAKmkSsNEtudicL0jiwsXuR+uubMX/80vU9+CGmhcpW4YAgGxKQJMHxuaJ9McLkKLFLBNOA7yhXbBjgEiI8bu9q8CQQC4mZknypD5Fguqfynwr9caP+ZU/4PH4nns9Qr34kWVCEPskS+TNfSCay4uu//KGUYMxOd0W6nNFu/zU2V+IDctAkARaUU1jXPe5pEYvUJfVxicesS+KGmVjN8nj8R3+NfFmLgeioWk8wm9FLLtwsmEYUXNZygPieLP2VF2VdqUopE7";
    	String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCb+/UoaGcgE/WFvDhEaxypc+d6Q6npcojtYpYFexIQYHB9OaWHkKe2fR8l6JOeiQYla5G14UoSvafjhr14bEMeQ0W6TJqFS9cEMKHijMlq1hQEXmZbqzq2E6TJGPR5a+LxNOb0Lb7nc5uR8twd2k3rf3N8NxjYN3DF8um8L6TGswIDAQAB";
    	String inputStr = "加密的字符串";
//    	byte[] encodedData = RSACoder.encryptByPublicKey(inputStr, publicKey);
//    	System.out.println(RSACoder.encryptBASE64(encodedData));
    	String fr="ILWjjYo2RwWeVI8esAX0wWKKloBoDzkc0wEqPTh0a4ARVdkBd0tBCufm9K6tEouZPNSs3aeK4oJTII3JIacQ3/NQJ/VOaGybA2BO20xIPmOLo550jioDR3LO8ztJfJfY+I7X7IogofZx0TIopcybIUTi7Bxu4GvTm+511OM3p5c=";
    	byte[] encodedData=RSACoder.decryptBASE64(fr);
        byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
                privateKey);
        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
	}
}