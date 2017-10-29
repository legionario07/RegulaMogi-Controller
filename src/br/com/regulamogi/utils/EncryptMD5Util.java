package br.com.regulamogi.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class EncryptMD5Util {

	/**
	 * 
	 * @param senha
	 *            Recebe uma String como parametro e Encriptar com MD5
	 * @return Um string encriptada
	 */
	public static String getEncryptMD5(String senha) {

		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		return hash.toString(16);

	}
}
