package com.example.qqtest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 计算文件的MD5
 * 
 * @author Administrator
 * 
 */
public class MD5Util {
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	protected static MessageDigest messageDigest = null;
	static {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算文件的MD5
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5String(String fileName) throws IOException {
		File f = new File(fileName);
		return getFileMD5String(f);
	}

	/**
	 * 获取字符串的MD5
	 * @param str
	 * @return
	 */
	public static String getStringMD5(String str) {
		messageDigest.update(str.getBytes());
		return bufferToHex(messageDigest.digest());
	}

	/**
	 * 计算文件的MD5
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5String(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		in.close();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		messageDigest.update(byteBuffer);
		return bufferToHex(messageDigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}
	
	public static void main(String[] args) {
		String expect = "a384b6463fc216a5f8ecb6670f86456a";
		String actual = MD5Util.getStringMD5("qwert");
		System.out.println(expect.equals(actual));
	}

}