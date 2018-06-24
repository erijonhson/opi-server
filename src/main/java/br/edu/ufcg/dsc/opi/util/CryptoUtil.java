package br.edu.ufcg.dsc.opi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptoUtil {

	private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public static String hashPassword(String password) {
		return bCryptPasswordEncoder.encode(password);
	}

	public static boolean matches(String password, String hash) {
		return bCryptPasswordEncoder.matches(password, hash);
	}

}
