package objects;

public abstract class Manager {
	
	
	//Phuong thuc truu tuong
	public abstract String getInfo(Person p);
	
	
	//Phuong thuc tuong minh
	public String getDetails(Person p) {
		return this.getInfo(p);
	}
	
	
	
}
