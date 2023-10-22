package objects;

public class Person implements Comparable<Person>{
//	Constants
	public static final String FIRSTNAME = "No FirstName";
	public static final String LASTNAME = "No LastName";
	public static final byte AGE = 0;
	public static final Address ADDRESS = new Address();

//	Object's properties
	private String firstName;
	private String lastName;
	private byte age;

	private Address address;
//	đưa về dạng chuẩn hoá, k dư thừa, tìm kiếm chậm chạp
	
//	private String cityName;
//	private String districtName;
//	private String streetName;
//	dạng chưa chuẩn hoá 
	
	

//	Constructor methods
	public Person() {
		this(Person.FIRSTNAME, Person.LASTNAME, Person.AGE, Person.ADDRESS);
	}

	public Person(byte age) {
		this(Person.FIRSTNAME, Person.LASTNAME, (byte) age, Person.ADDRESS);
	}

	public Person(String firstName, byte age, Address address) {
		this(firstName, Person.LASTNAME, (byte) age, address);
	}

//	public Person(String firstName, String lastName, byte age, Address address) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.age = age;
//		this.address = address;
//	}

	public Person(String firstName, String lastName, byte age, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
//		this.address = new Address();
		// Cach 1: Gán địa chỉ trong bộ nhớ
		this.address = address;
//	cả 2 tk này cùng trỏ đến một chỗ để lấy giá trị
		// khi gán 1 thằng về null thì chỉ còn 1 thằng trỏ đến địa chỉ đó
//		còn có đối tượng trỏ đến thì còn dùng bộ nhớ và ngược lại

		// Cach2: Khởi tạo vùng nhớ mới và tạo giá trị
		this.address = new Address(address);
//		với cách này thì cần dùng constructor đb loại 3
		// vì tính độc lập về dữ liệu nên vẫn đề cao cách số 2 hơn
	}

// Getter methods
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public byte getAge() {
		return age;
	}

	public Address getAddress() {
		return this.address;
	}

//	Setter methods
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setAddress(String cityName, String districtName, String streetName) {
		this.address = new Address(cityName, districtName, streetName);
	}

//	@Override
//	public String toString() {
//		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "- address="
//				+ this.address.toString() + "]";
//	}
	
//	@Override
//	public String toString() {
//		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
//	}
	
	
	

	protected void finalize() throws Throwable {
		System.gc();
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [firstName = ");
		builder.append(firstName);
		builder.append(", lastName = ");
		builder.append(lastName);
		builder.append(", age = ");
		builder.append(age);
		builder.append(", address = ");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}

	public static void main(String[] args) {
		Address address = new Address("Hanoi", "BacTuLiem", "Nhon");
		
//		Khai báo và khởi tạo đối tượng
		Person p;
		Person p1 = new Person();
		Person p2 = new Person((byte) 14);
//		Person p3 = new Person("Hoàng", (byte) 14);
		Person p4 = new Person("Hoàng", "Nguyễn Việt", (byte) 22, address);
		System.out.println(p1);
		System.out.println(p2);
//		System.out.println(p3);
		System.out.println(p4);

	}

	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		
		// thằng đằng trước - thằng đằng sau (T - S == 0 ?)
		return this.age - o.getAge();
	}
}
