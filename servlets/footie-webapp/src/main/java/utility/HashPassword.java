package utility;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
	
	public String hashPassword(String password){
		MessageDigest algorithm = null;
		try {
			algorithm = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		algorithm.reset();
		algorithm.update(password.getBytes());
		byte[] messageDigest = algorithm.digest();

		// Now to print out a hex representation of the digest.
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < messageDigest.length; i++) {
			hexString.append(Integer.toHexString(255 & messageDigest[i]));
			hexString.append(".");
		}
		return hexString.toString();
	}
}
