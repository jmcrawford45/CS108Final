package security;

import java.security.*;
import java.util.*;

public class Security {
	static final int SALT_LENGTH = 4;
	public static String getSalt(Random rand){
		String salt = "";
		for(int i = 0; i < SALT_LENGTH; i++) salt += (char)rand.nextInt(256);
		return salt;
	}
	public static String getHashed(String password, String salt){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		String result = password + salt;
		md.update(result.getBytes());
		return hexToString(md.digest());
	}
	/*
	 * Given a byte[] array, produces a hex String, such as "234a6f". with 2
	 * chars for each byte in the array. (provided code)
	 */
	private static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff; // remove higher bits, sign
			if (val < 16)
				buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
}
