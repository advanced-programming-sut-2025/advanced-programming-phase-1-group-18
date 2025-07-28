package io.github.group18.Network.common.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class MD5Hash {
	public static String HashFile(String filePath) {
		try (InputStream fis = new FileInputStream(filePath);
			 DigestInputStream dis = new DigestInputStream(fis, MessageDigest.getInstance("MD5"))) {

			// 3. Read file in chunks via DigestInputStream
			byte[] buffer = new byte[4096];
			while (dis.read(buffer) != -1) {
				// Reading through DigestInputStream automatically updates the digest
			}

			// 4. Get digest from the stream
			byte[] digest = dis.getMessageDigest().digest();

			// 5. Convert digest to hex string
			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();

		} catch (Exception e) {
			// 6. Handle errors
			System.err.println("Error calculating MD5 hash for file: " + filePath);
			e.printStackTrace();
			return null;
		}
	}
}

