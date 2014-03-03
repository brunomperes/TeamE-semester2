package uk.ac.gla.dcs.psd.team.e.components.db;

import java.util.Collection;
import java.util.List;

import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.IIdentifiable;

public interface IDatabase {

	public boolean delete(String ID, Class<? extends IIdentifiable> cl);

	public Object get(String ID, Class<? extends IIdentifiable> cl);

	boolean update(IIdentifiable newObject, Class<? extends IIdentifiable> cl);

	public List<IIdentifiable> getAll(Class<? extends IIdentifiable> cl);

	public boolean add(IIdentifiable o, Class<? extends IIdentifiable> cl);
	
	public <T extends Collection<? extends IIdentifiable>> boolean addAll(T c, Class<? extends IIdentifiable> cl);

}
