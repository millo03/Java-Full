package objects;

public class TestManager {
	public static void main(String[] args) {
		Address address = new Address();

		Person student = new Student("Tuấn", "Phạm Minh", (byte) 20, address, 12_345, "CNTT");
		Person ePerson = new Employee("Hoang", "Nguyen", (byte) 22, address, 12_000_000, "Nhan vien");

//		-------------------------------------------------------
		Manager studentManager = new StudentManager();
		Manager employeeManager = new EmployeeManager();
		
		System.out.println(studentManager.getDetails(student));
		System.out.println(employeeManager.getDetails(ePerson));
		
		
	}
}
