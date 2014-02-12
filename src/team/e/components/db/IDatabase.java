package team.e.components.db;

import java.util.List;

import team.e.components.sysfunc.timetable.IIdentifiable;

public interface IDatabase {

	public boolean delete(String ID, Class<? extends IIdentifiable> cl);

	public Object get(String ID, Class<? extends IIdentifiable> cl);

	boolean update(IIdentifiable newObject, Class<? extends IIdentifiable> cl);

	public List<IIdentifiable> getAll(Class<? extends IIdentifiable> cl);

	public boolean add(Object o, Class<? extends IIdentifiable> cl);

}
