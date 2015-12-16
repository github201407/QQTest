package com.example.qqtest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

/**
 * 正则表达式：验证手机号、邮箱号、微信号 格式
 * @author Administrator
 *
 */
public class RegExpUtils {
	
	/**
	 * 验证邮箱格式
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String pattern = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
		return matchPattern(pattern, email);
	}

	/**
	 * 验证手机号格式
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		String pattern = "^(13[0-9]|14[5|7]|15[^4,\\D]|18[0-9]|17[0|6|7|8])\\d{8}$";
		return matchPattern(pattern, mobile);
	}

	/**
	 * 验证微信号格式：支持6-20个字母、数字、下划线和减号，必须以字母开头
	 * @param wxh
	 * @return
	 */
	public static boolean isWxh(String wxh) {
		String pattern = "^[a-zA-Z]{1}[a-zA-Z0-9_-]{5,19}$";
		return matchPattern(pattern, wxh);
	}

	private static boolean matchPattern(String pattern, String input) {
		try {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(input);
			return m.matches();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
