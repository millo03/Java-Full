package objects;

import java.util.ArrayList;

public class Student extends Person {
	public static final int STUDENT_ID = 0;
	public static final String STUDENT_SPECIALY = "NONE";

//	Student's properties:
	private int student_id;
	private String student_specialy;

	public Student() {
		this(Student.FIRSTNAME, Student.LASTNAME, Student.AGE, Student.ADDRESS, Student.STUDENT_ID,
				Student.STUDENT_SPECIALY);
	}

	public Student(int student_id, String student_specialy) {
		super();
		this.student_id = student_id;
		this.student_specialy = student_specialy;
	}

	public Student(Student student) {
		this(STUDENT_ID, STUDENT_SPECIALY);
	}

	public Student(String firstName, String lastName, byte age, Address address, int student_id,
			String student_specialy) {

		// Khởi tạo thành công lớp cha (Person)
		super(firstName, lastName, age, address);

//		Sau đó gán giá trị cho các thuộc tính lớp con (Student) (phải có cha trước rồi mới có con)
		this.student_id = student_id;
		this.student_specialy = student_specialy;
	}

	public int getStudent_id() {
		return student_id;
	}

	public String getStudent_specialy() {
		return student_specialy;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public void setStudent_specialy(String student_specialy) {
		this.student_specialy = student_specialy;
	}

	@Override
	public String toString() {
		return "Student [" + super.toString() + "student_id=" + student_id + ", student_specialy=" + student_specialy
				+ "]";
	}
	// Vừa được viết ở student vừa được viết ở student thì được gọi là ghi đè

	public static void main(String[] args) {
		Address address = new Address();
		// student ben tria la lopo khai bao, student ben phai la lop khoi tao
		// upcasting: lop khai bao la lop cha, lop khoi tao la lop con
		// mot the hien doi tong duoc khai bao voi lop nao thi se dung phuong thuc o lop
		// no
		// tru khi bi override thi se goi den phuong thuc o lop con(lop khoi tao)
		// da hinh: goi the nay lai ra ket qua the khac
		Person student = new Student("Tuấn", "Phạm Minh", (byte) 20, address, 12_345, "CNTT");
		Person ePerson = new Employee("Hoang", "Nguyen", (byte)22, address, 12_000_000, "Nhan vien");
		System.out.println(ePerson);
		System.out.println(student);
	}

}
