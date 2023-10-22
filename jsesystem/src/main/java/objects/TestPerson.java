package objects;

public class TestPerson {
	public static void main(String[] args) {
		Address address = new Address();

		Person student = new Student("Tuấn", "Phạm Minh", (byte) 20, address, 12_345, "CNTT");
		Person ePerson = new Employee("Hoang", "Nguyen", (byte) 22, address, 12_000_000, "Nhan vien");
		System.out.println(ePerson);
		System.out.println(student);
	}
}
