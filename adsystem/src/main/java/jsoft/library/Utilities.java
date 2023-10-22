package jsoft.library;

import javax.servlet.ServletRequest;

import net.htmlparser.jericho.CharacterReference;

public class Utilities {
	
	/**
	 * Phương thức lấy giá trị byte của tham số
	 * @param request- lưu trữ dữ liệu vào
	 * @param name
	 * @return
	 */
	

	public static byte getByteParam(ServletRequest request, String name) {// lấy giá trị của tham số theo kiểu byte
		byte value = -1;
		try {
			String str_value = request.getParameter(name);
			if (str_value != null && str_value.length() > 0) {
				value = Byte.parseByte(str_value);
			}
		}catch(NumberFormatException ex) {
			System.out.println("Lỗi giá trị tham số");
			ex.printStackTrace();
		}
		return value;
	}

	public static short getShortParam(ServletRequest request, String name) {
		short value = -1;
		try {
			String str_value = request.getParameter(name);
			return str_value != null && str_value.length() > 0 ? Short.parseShort(str_value) : -1;
		}catch(NumberFormatException ex) {
			System.out.println("Lỗi giá trị tham số");
			ex.printStackTrace();
		}
		return value;
	}

	public static int getIntParam(ServletRequest request, String name) {
		int value = -1;
		try {
			String str_value = request.getParameter(name);
			return str_value != null && str_value.length() > 0 ? Integer.parseInt(str_value) : -1;
		}catch(NumberFormatException ex) {
			System.out.println("Lỗi giá trị tham số");
			ex.printStackTrace();
		}
		return value;
	}

	public static String encode(String strUnicode) { // mã hóa
		return CharacterReference.encode(strUnicode);
	}

	public static String decode(String strHTML) { // giải mã
		return CharacterReference.decode(strHTML);
	}

	public static void main(String[] args) {
		
		System.out.println(Utilities.decode("Đỗ Thị Huyền"));
	}

}
