package cn.gap.tool;

/**
 * 字符串工具类
 * @author genganpeng
 *
 */
public class StringUtils {
	/**
	 * 字符串是否为空
	 * @param str 字符串
	 * @return 为空返回true
	 */
	public static boolean isEmpty(String str) {
		if (null == str || str.equals("")) {
			return true;
		}
		return false;
	}
}
