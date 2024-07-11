package kh.study.cjy.etc;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESCrypto {
	private static final String KEY = "mysecretkey12345"; 
	private static final String ALGORITHM = "AES";
    
    public static String encrypt(String plainText) throws NoSuchAlgorithmException, NoSuchPaddingException, 
    								InvalidKeyException, InvalidAlgorithmParameterException,
    								IllegalBlockSizeException, BadPaddingException {
    	SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    	}

    	public static String decrypt(String encryptedText) throws NoSuchAlgorithmException, NoSuchPaddingException, 
		InvalidKeyException, InvalidAlgorithmParameterException,
		IllegalBlockSizeException, BadPaddingException {
    		   SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
    	        Cipher cipher = Cipher.getInstance(ALGORITHM);
    	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
    	        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
    	        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
    	        return new String(decryptedBytes, StandardCharsets.UTF_8);
    	}
}
