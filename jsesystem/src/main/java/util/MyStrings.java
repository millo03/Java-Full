package util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MyStrings {

	public static int countChar(String str, char ch, boolean isIgnoreCase) {
		int count = 0;
		int length = str.length();
		if (isIgnoreCase) {
			str = str.toLowerCase();
			ch = Character.toLowerCase(ch);
			// Lớp đối tượng làm việc với kiểu nguyên thuỷ của nó
		}
		for (int i = 0; i < length; ++i)
			if (str.charAt(i) == ch)
				++count;
		return count;
	}

	public static String formatString(String str, formatAllow fa) {
		// Danh sách các kí tự đặc biệt
		char[] chs = { '`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', ';', ':', '\'',
				'"', '<', '>', '?', '/', '\\', '|' };

		// Loại bỏ kí tự đặc biệt trong chuỗi
		for (char ch : chs) {
			str = str.replace(ch, ' ');
		}

		// Loại bỏ dấu cách
		str = str.trim();
		while (str.indexOf("  ") != -1) {
			str = str.replace("  ", " ");
		}

		str.replace(" . ", ". ");
		str.replace(" , ", ", ");

		// Xử lý theo tên riêng/ câu văn bản/...
		switch (fa) {
		case NAME:
		case FULLNAME:
			String[] words = str.split(" ");

			String tmp = "";

			for (String w : words) {
				w = w.toLowerCase();
				tmp += Character.toUpperCase(w.charAt(0)) + w.substring(1) + " ";
			}
			str = tmp.trim();
			break;

		case SENTENCE:
			str = Character.toUpperCase(str.charAt(0)) + str.substring(1) + ". ";
		default:
		}
		return str;
	}

	public static int countWords(String str) {
		// Chuẩn hoá
		str = MyStrings.formatString(str, formatAllow.DEFAULT);

		return MyStrings.countChar(str, ' ', false) + 1;
	}

	public static HashMap<String, Integer> statisticWords(String str, String ch_split) {
		HashMap<String, Integer> tmpHashMap = new HashMap<String, Integer>();
		String[] tmp_words = str.split(ch_split);
		for (String w : tmp_words) {
			if (!w.equalsIgnoreCase("")) {
				w = w.trim();
//				if (tmpHashMap.get(w) == 0) {
//					tmpHashMap.put(w, 1);
//				} else {
//					tmpHashMap.replace(w, tmpHashMap.get(w) + 1);
//				}

				if (tmpHashMap.containsKey(w)) {
					tmpHashMap.replace(w, tmpHashMap.get(w) + 1);
				} else {
					tmpHashMap.put(w, 1);
				}
			}
		}
		return tmpHashMap;
	}

	public static void printStatistic(HashMap<String, Integer> sta) {
		TreeMap<String, Integer> tmpMap = new TreeMap<String, Integer>(sta);
		
		
		for (Map.Entry<String, Integer> e : tmpMap.entrySet()) {
			System.out.println(e.getKey() + " - " + e.getValue());
		}
	}

	public static void main(String[] args) {
		String str = "Hello Everyone";
		String str2 = "nguyễN       việt      Hoàng";
		String str3 = "xin   chao   1 nguoi   ";
		String str4 = "@@mtm@@adc@adb@abc@@mtm@@";
		MyStrings.printStatistic(MyStrings.statisticWords(str4, "@"));
//		System.out.println(MyStrings.countWords(str3));
//		System.out.println(MyStrings.formatString(str3, formatAllow.SENTENCE));
//		System.out.println(MyStrings.formatString(str2, formatAllow.FULLNAME));
//		System.out.println(MyStrings.countChar(str, 'e', false));
	}
}

enum formatAllow {
	NAME, FULLNAME, SENTENCE, DEFAULT

}
