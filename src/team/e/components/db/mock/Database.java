package team.e.components.db.mock;

import java.util.ArrayList;
import java.util.List;

import team.e.components.db.IDatabase;
import team.e.components.sysfunc.timetable.Course;
import team.e.components.sysfunc.timetable.Session;
import team.e.components.sysfunc.timetable.TimetableSlot;

public class Database implements IDatabase {
	
	List<Course> allCourses = new ArrayList<Course>();
	List<Session> allSession = new ArrayList<Session>();
	List<TimetableSlot> allTimetableSlot = new ArrayList<TimetableSlot>();
	
	@Override
	public List<Object> getAll(Class cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String ID, Class cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Object o, Class<?> cl) {
		cl.getName();
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String ID, Class cl) {
		// TODO Auto-generated method stub
		return false;
	}


}
