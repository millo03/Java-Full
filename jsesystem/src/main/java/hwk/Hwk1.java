package hwk;

import java.util.Scanner;

public class Hwk1 {
	static Scanner scanner = new Scanner(System.in);

	static void input() {
		byte b = scanner.nextByte();
		short s = scanner.nextShort();
		long l = scanner.nextLong();
		char c = scanner.next().charAt(0);
		double d = scanner.nextDouble();
	}

	static int getFactorial(int n) {
		int res = 1;
		for (int i = 1; i <= n; i++) {
			res *= i;
		}
		return res;
	}

	static int getCmn(int n, int m) {
		return getFactorial(n) / (getFactorial(n - m) * getFactorial(m));
	}

	static int lcm(int a, int b) {
		int r;
		while (b != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	static boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n); ++i) {
			if (n % i == 0)
				return false;
		}
		return n > 1;
	}

	static boolean isTriange(int a, int b, int c) {
		return a + b > c && a + c > b && b + c > a;
	}
	
	static double areaTrangle(int a, int b, int c) {
		if(isTriange(a, b, c)) {
			double s = (a+b+c)/2;
			return Math.sqrt(s*(s-a)*(s-b)*(s-c));
		}else {
			return -1;
		}
	}
	
	static double areaTrapezoid(int a, int b, int h) {
		return (a+b)*h/2;
	}
	
	static double areCircle(int r) {
		return r*r*3.14;
	}
	
	

	public static void main(String[] args) {
		System.out.println(getCmn(4, 2));
		System.out.println(lcm(4, 3));
		System.out.println(areaTrangle(3, 4, 5));
	}
}
