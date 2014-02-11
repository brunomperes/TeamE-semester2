package team.e.components.db;

import java.util.List;

public interface IDatabase {

	public List<Object> getAll(Class<?> cl);

	public Object get(String ID, Class<?> cl);

	public boolean add(Object o, Class<?> cl);

	public boolean delete(String ID, Class<?> cl);
	
	public boolean update(Object newObject, String ID, Class<?> cl);

}
