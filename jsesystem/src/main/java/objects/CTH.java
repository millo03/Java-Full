package objects;

import java.util.*;

public interface CTH {
	public ArrayList<Person> searchPerson(String name);

	public ArrayList<Person> searchPerson(byte min_age, byte max_age);

	public ArrayList<Person> searchPerson(Address address);

	public ArrayList<Person> searchPerson(Person similarPerson);
}
