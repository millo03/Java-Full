package objects;

public class Employee extends Person {
	public static final int EMPLOYEE_NET = 0;
	public static final String EMPLOYEE_POSITION = "No Position";

	private int employee_net;
	private String employee_position;

	
	
	
	public Employee(int net, String position) {
		super();
		this.employee_net = net;
		this.employee_position = position;
	}
	
	public Employee(String firstName, String lastName, byte age, Address address, int net, String position) {
		super(firstName, lastName, age, address);
		this.employee_net = net;
		this.employee_position = position;
	}

	public int getNet() {
		return employee_net;
	}

	public String getPosition() {
		return employee_position;
	}

	public void setNet(int net) {
		this.employee_net = net;
	}

	public void setPosition(String position) {
		this.employee_position = position;
	}

	@Override
	public String toString() {
		return "Employee [net=" + employee_net + ", position=" + employee_position + "]";
	}
	
	
	public static void main(String[] args) {
		Address address = new Address();
		Person ePerson = new Employee("Hoang", "Nguyen", (byte)22, address, 12_000_000, "Nhan vien");
	}

}
