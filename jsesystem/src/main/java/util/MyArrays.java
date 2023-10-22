package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import objects.Person;

public class MyArrays {

	public static int[] generateArray(int n) {
		int[] arrInt = new int[n];
		for (int i = 0; i < arrInt.length; ++i) {
			arrInt[i] = (int) (Math.random() * 100);
		}
		return arrInt;
	}

	public static int[][] generateArray(int rows, int columns) {
		int[][] arrInt = new int[rows][columns];
		for (int i = 0; i < rows; ++i) {
			arrInt[i] = generateArray(columns);
		}
		return arrInt;
	}

	public static Person[] generatePersons(int n) {
		Person[] persons = new Person[n];

		String[] firstNames = { "Anh", "Bảo Anh", "Anh Bảo", "Hùng", "Huyền", "Trang", "Minh", "Trang", "Minh", "Hân",
				"Minh Anh", "Tuấn Anh", "Anh Tuấn", "Châu", "Châu", "Châu Anh", "Minh Châu", "Thuỷ", "Đạt", "Hoàng",
				"Yến", "Yến Anh", "Vũ", "Khải", "Linh", "Huy", "Thọ", "Chiến" };

		String[] lastNames = { "Hoàng", "Lê", "Nguyễn", "Bùi", "Đoàn", "Đào", "Dương", "Ngô", "Hồ", "Trần", "Lương",
				"Ma", "Vương", "Phạm", "Phan", "Lò", "Quách", "Vũ", "Cao", "Đàm" };

		int randomIndex;
		byte randomAge;
		for (int i = 0; i < n; ++i) {
			// Cấp phát bộ nhớ cho từng phần tử mảng đối tượng
			persons[i] = new Person();

			randomIndex = (int) (Math.random() * firstNames.length);
			persons[i].setFirstName(firstNames[randomIndex]);

			randomIndex = (int) (Math.random() * lastNames.length);
			persons[i].setLastName(lastNames[randomIndex]);

			randomAge = (byte) (Math.random() * 3 + 18);
			persons[i].setAge((byte) randomAge);
		}
		return persons;
	}

	public static void printArray(int[] arrInt) {
//		for (int item : arrInt) {
//			System.out.print(item + " ");
//		}

//		Cach 3
		System.out.println(Arrays.toString(arrInt));

	}

	public static void printArray(int[][] arrInt) {

//		C1
//		for (int[] item : arrInt) {
//			printArray(item);
//		}

//		C2
//		System.out.println(Arrays.deepToString(arrInt));

//		C3
//		System.out.println(Arrays.toString(item));

//		C4: Sử dụng Arrays (chuyển đổi mảng ngyên thuỷ sang mảng đối tượng)
		Integer[] data = new Integer[arrInt.length];
		Arrays.setAll(data, i -> arrInt[i]);
		System.out.print(Arrays.toString(data));
		System.out.println();

	}

	public static void printArray(Person[] persons) {
		for (Person person : persons) {
			System.out.println(person.toString());
		}
	}

	public static void printArray(ArrayList<Person> persons) {
//		for(Person person:persons) {
//			System.out.println(person);
//		}

		// lambda expression
		persons.forEach(item -> System.out.println(item));
	}

	public static Person[] searchPerson(Person[] persons, String targetFirstName) {
		Person[] foundPersons = null;

		int foundCount = 0;
		for (Person person : persons) {
			if (person.getFirstName().contains(targetFirstName)) {
				++foundCount;
			}
		}

		foundPersons = new Person[foundCount];
		foundCount = 0;
		for (Person person : persons) {
			if (person.getFirstName().contains(targetFirstName)) {
				foundPersons[foundCount++] = person;
			}
		}
		return foundPersons;
	}

	public static ArrayList<Person> searchPersons2(Person[] persons, String targetFirstName) {
		ArrayList<Person> foundPersons = new ArrayList<Person>();
		for (Person person : persons) {
			if (person.getFirstName().contains(targetFirstName)) {
				foundPersons.add(person);
			}
		}
		return foundPersons;
	}

	public static Person[] sortArray(Person[] persons, boolean isAsc) {
		int arrLength = persons.length;
		Person[] resultArrayList = new Person[arrLength];
		for (int i = 0; i < arrLength - 1; ++i) {
			for (int j = i + 1; j < arrLength; ++j) {
				if (persons[i].getFirstName().compareTo(persons[j].getFirstName()) < 0) {

				}
			}
		}
		return resultArrayList;
	}

	public static ArrayList<Person> sortByAge(Person[] list, boolean isInc) {
		// Chuyển mảng đối tượng sang tập hợp
		ArrayList<Person> tmpPersons = new ArrayList<Person>();

		// Sao chép phần tử
		Collections.addAll(tmpPersons, list);

		// Thực hiện sắp xếp
		if (isInc) {
			Collections.sort(tmpPersons);
		} else {
			Collections.sort(tmpPersons, Collections.reverseOrder());
		}
		return tmpPersons;
	}

	public static ArrayList<Person> sortByName(Person[] list, boolean isInc) {
		// Chuyển mảng đối tượng sang tập hợp
		ArrayList<Person> tmpPersons = new ArrayList<Person>();

		// Sao chép phần tử
		Collections.addAll(tmpPersons, list);

		// Xác định điều kiện sắp xếp
		sortByName sortByName = new sortByName();

		// Thực hiện sắp xếp
		if (isInc) {
			Collections.sort(tmpPersons, new sortByName());
		} else {
			Collections.sort(tmpPersons, new sortByName().reversed());
		}
		return tmpPersons;
	}

	public static int[] sortArray(int[] arrInt, boolean isAsc) {
		int arrLength = arrInt.length;
		byte ori = isAsc ? (byte) 1 : (byte) -1;
		for (int i = 0; i < arrLength - 1; ++i) {
			for (int j = i + 1; j < arrLength; ++j) {
				if (ori * arrInt[i] > ori * arrInt[j]) {
					int tmp = arrInt[i];
					arrInt[i] = arrInt[j];
					arrInt[j] = tmp;
				}
			}
		}
		return arrInt;
	}

	public static Integer[] sortArrayV2(int[] arrInt, boolean isINC) {
		Integer[] data = new Integer[arrInt.length];
		Arrays.setAll(data, i -> arrInt[i]);
		if (isINC) {
			Arrays.sort(data);
		} else {
			Arrays.sort(data, Collections.reverseOrder());
		}
		return data;
	}
	
	public static Integer[] sortArrayV2(Integer[] arrInt, boolean isINC) {
		if (isINC) {
			Arrays.sort(arrInt);
		} else {
			Arrays.sort(arrInt, Collections.reverseOrder());
		}
		return arrInt;
	}

	public static int[][] sortArray(int[][] arrInt, boolean isINC) {
		int row = arrInt.length;
		int col = arrInt[0].length;

		Integer[] tmp = new Integer[row * col];

		// Sao chép giá trị
		int r = 0, c = 0;
		for (int i = 0; i < tmp.length; ++i) {
			tmp[i] = arrInt[r][c++];
			if (c == col) {
				++r;
				c = 0;
			}
		}
		tmp = MyArrays.sortArrayV2(tmp, isINC);
		r = 0; c = 0;
		for(int i = 0;i<tmp.length; ++i) {
			arrInt[r][c++] = tmp[i++];
			if(c==col) {
				++r;
				c = 0;
			}
		}
		return arrInt;
	}

	public static void main(String[] args) {
//		int[] arrInt = generateArray(20);
//		printArray(arrInt);
//		arrInt = sortArray(arrInt, true);
//		printArray(arrInt);
//		int[][] arrInt = generateArray(5, 5);
//		printArray(arrInt);

//		Person[] persons = generatePersons(20);
//		printArray(persons);
//		System.out.println("----------------");
//		Person[] foundPersons = searchPerson(persons, "Anh");
//		if(foundPersons.length==0) {
//			System.out.println("Not found");
//		}else {
//			printArray(foundPersons);
//		}
		
//		ArrayList<Person> foundPersons = searchPersons2(persons, "Anh");
//		if(foundPersons.size()==0) {
//			System.out.println("Not found");
//		}else {
//			printArray(foundPersons);
//		}
		
//		ArrayList<Person> foundPersons = MyArrays.sortByAge(persons, false);
//		printArray(foundPersons);
		
//		ArrayList<Person> foundPersons = MyArrays.sortByName(persons, true);
//		foundPersons = MyArrays.sortByAge(persons, true);
//		printArray(foundPersons);
		
		int[][] arrInt = MyArrays.generateArray(5, 10);
		MyArrays.printArray(arrInt);
		int[][] res  = MyArrays.sortArray(arrInt, true);
		MyArrays.printArray(res);
		
		
	}
}

// Lớp nội, tạo ra lớp riêng như này thì là lớp tường minh, chèn cả khối code vào trong hàm thì là khối ẩn danh
class sortByName implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		String name1 = o1.getFirstName().trim();
		String name2 = o2.getFirstName().trim();

		// Chỉ lấy tên sau cùng (1 từ)
		int at = name1.lastIndexOf(' ');
		if (at != -1) {
			name1 = name1.substring(at + 1);
		}

		at = name2.lastIndexOf(' ');
		if (at != -1) {
			name2 = name2.substring(at + 1);
		}

		// TODO Auto-generated method stub
		return name1.compareToIgnoreCase(name2);
	}

}
