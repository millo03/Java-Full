package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface BTTU extends CTX, CTH, GDS {
	public List<Person> getAll();

	public List<Person> getPerson(Address address);

	public ArrayList<Person> getName();

	public ArrayList<Person> getName(Address address);

	public HashMap<Integer, Person> getPositions();

	public HashMap<Integer, Person> getPositions(Address address);

}
