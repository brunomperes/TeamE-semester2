package uk.ac.gla.dcs.psd.team.e.components.db.fake;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import uk.ac.gla.dcs.psd.team.e.components.db.IDatabase;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Course;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.IIdentifiable;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Session;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.TimetableSlot;

/**
 * The current implementation of the database, does not check for duplicated IDs
 * when adding to a list.
 * 
 * It is a fake package as it does not implement a real database, but an in-memory simulation. 
 */
public class Database implements IDatabase {


	HashMap<String, List<IIdentifiable>> tableMap = new HashMap<String, List<IIdentifiable>>();

	// TODO suppress this warning if everything's fine.
	@Override
	public List<IIdentifiable> getAll(Class<? extends IIdentifiable> cl) {
		return tableMap.get(cl.getName());
	}

	@Override
	public Object get(String ID, Class<? extends IIdentifiable> cl) {
		List<IIdentifiable> allElements = getAll(cl);
		for (IIdentifiable element : allElements) {
			if (element.getId().equals(ID))
				return (element);
		}
		return null;
	}

	private void addTable(Class<? extends IIdentifiable> cl) {
		String className = cl.getName();
		if (!tableMap.containsKey(className)) {
			tableMap.put(className, new ArrayList<IIdentifiable>());
		}
	}

	@Override
	// TODO suppress this warning if everything's fine.
	public boolean add(IIdentifiable o, Class<? extends IIdentifiable> cl) {
		addTable(cl);
		tableMap.get(cl.getName()).add(o);
		return true;
	}
	
	public <T extends Collection<? extends IIdentifiable>> boolean addAll(T c, Class<? extends IIdentifiable> cl) {
		for (IIdentifiable i: c){
			add(i, cl);
		}
		return true;
	}

	@Override
	public boolean delete(String ID, Class<? extends IIdentifiable> cl) {
		List<IIdentifiable> allElements = getAll(cl);
		for (int i = 0; i < allElements.size(); i++) {
			if (allElements.get(i).getId().equals(ID)) {
				allElements.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean update(IIdentifiable newObject,
			Class<? extends IIdentifiable> cl) {
		String ID = newObject.getId();
		List<IIdentifiable> allElements = getAll(cl);
		for (int i = 0; i < allElements.size(); i++) {
			if (allElements.get(i).getId().equals(ID)) {
				allElements.set(i, (IIdentifiable) newObject);
				return true;
			}
		}
		return false;
	}
/*
	private void populateDb() {
		add(new Course("PSD3", "1"), Course.class);
		add(new Session("0001", "tut", 1, 60, 11, true), Session.class);
		add(new TimetableSlot("0001", new Date(15, 10, 2013), "Boyd Orr 720",
				"1105053", 1), TimetableSlot.class);
	}*/

}
