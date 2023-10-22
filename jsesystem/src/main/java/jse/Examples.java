package jse;

public class Examples {

	/**
	 * Phương thức kiểm tra n có phải nguyên tố? <br>
	 * <i>Cập nhật ngày 12/02/2023</i>
	 * 
	 * @author Hoang Nguyen Viet
	 * @param n - giá trị cần kiểm tra
	 * @return - kết quả trả về đúng hay sai?
	 */
	public static boolean isPrime(int n) {
		for (int i = 2; i <= (int) (Math.sqrt(n)); ++i) {
			if (n % i == 0)
				return false;
		}
		return n > 1;
	}

	/**
	 * Phương thức tìm UCLN của hai số nguyên dương <br>
	 * <i>Cập nhật ngày 12/02/2023</i>
	 * 
	 * @author Hoang Nguyen
	 * @param a - Số thứ nhất
	 * @param b - Số thứ hai
	 * @return - Kết quả là giá trị nguyên hoặc -1 (không tồn tại UCLN)
	 */
	public static int lcm(int a, int b) {
		int r;
		while (b != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
	
	public static int lcm(int a, int b, int c) {
		return lcm(lcm(a, b), c);
	}

	public static void main(String[] args) {
		// Sinh ngẫu nhiên giá trị n
		int n = (int) (Math.random() * 100);

		// In thông tin ra màn hình
		System.out.println("Giá trị n = " + n);

		// Bên trái là khai báo, bên phải là khởi tạo bộ nhớ
		// Examples ex = new Examples();

		if (Examples.isPrime(n))
			System.out.println(n + " là số nguyên tố");
		else
			System.out.println(n + " không là số nguyên tố");
		System.out.println(Examples.lcm(3, 6));
		System.out.println(Examples.lcm(3, 6, 4));
	}
}
