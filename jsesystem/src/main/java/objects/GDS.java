package objects;

import java.util.List;

public interface GDS {
	public List<Person> statisticPerson(String name);

	public List<Person> statisticPerson(byte age);

	public List<Person> statisticPerson(Address address);

	public List<Person> statisticPerson(String name, Address address);
}
