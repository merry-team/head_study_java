package com.sharingcard.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAApi {
	private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };
	private static final int MAX_ENCRYPT_BLOCK = 117;
	private static final int MAX_DECRYPT_BLOCK = 128;
	String path = getClass().getResource("").getPath();

	public static String loadKeyByFile(String path, String keyName) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path + File.separator + keyName));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			throw new Exception("公钥数据流读取错误");
		} catch (NullPointerException e) {
		}
		throw new Exception("公钥输入流为空");
	}

	public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Utils.decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
		}
		throw new Exception("公钥数据为空");
	}

	public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Utils.decode(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法", e);
		} catch (InvalidKeySpecException e) {
			throw new Exception("私钥非法", e);
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空", e);
		}
	}

	public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");

			cipher.init(1, publicKey);

			int inputLen = plainTextData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;

			int i = 0;

			while (inputLen - offSet > 0) {
				byte[] cache;
				if (inputLen - offSet > 117) {
					cache = cipher.doFinal(plainTextData, offSet, 117);
				} else {
					cache = cipher.doFinal(plainTextData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * 117;
			}
			byte[] output = out.toByteArray();
			out.close();
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
		}
		throw new Exception("明文数据已损坏");
	}

	public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData) throws Exception {
		if (privateKey == null) {
			throw new Exception("加密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(1, privateKey);
			int inputLen = plainTextData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;

			int i = 0;

			while (inputLen - offSet > 0) {
				byte[] cache;
				if (inputLen - offSet > 117) {
					cache = cipher.doFinal(plainTextData, offSet, 117);
				} else {
					cache = cipher.doFinal(plainTextData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * 117;
			}
			byte[] output = out.toByteArray();
			out.close();
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
		}
		throw new Exception("明文数据已损坏");
	}

	public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");

			cipher.init(2, privateKey);

			int inputLen = cipherData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;

			int i = 0;

			while (inputLen - offSet > 0) {
				byte[] cache;
				if (inputLen - offSet > 128) {
					cache = cipher.doFinal(cipherData, offSet, 128);
				} else {
					cache = cipher.doFinal(cipherData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * 128;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
		}
		throw new Exception("密文数据已损坏");
	}

	public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData) throws Exception {
		if (publicKey == null) {
			throw new Exception("解密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");

			cipher.init(2, publicKey);

			int inputLen = cipherData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;

			int i = 0;

			while (inputLen - offSet > 0) {
				byte[] cache;
				if (inputLen - offSet > 128) {
					cache = cipher.doFinal(cipherData, offSet, 128);
				} else {
					cache = cipher.doFinal(cipherData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * 128;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
		}
		throw new Exception("密文数据已损坏");
	}

	public static String byteArrayToString(byte[] data) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			stringBuilder.append(HEX_CHAR[((data[i] & 0xF0) >>> 4)]);

			stringBuilder.append(HEX_CHAR[(data[i] & 0xF)]);
			if (i < data.length - 1) {
				stringBuilder.append(' ');
			}
		}
		return stringBuilder.toString();
	}

	public static String encryptInBase64(String privateKey, String param) throws Exception {
		return Base64Utils.encode(encrypt(loadPrivateKeyByStr(privateKey), param.getBytes("UTF-8")));
	}

	public static String decryptInBase64(String publicKey, String param) throws Exception {
		return new String(decrypt(loadPublicKeyByStr(publicKey), Base64Utils.decode(param)), "UTF-8");
	}
}
