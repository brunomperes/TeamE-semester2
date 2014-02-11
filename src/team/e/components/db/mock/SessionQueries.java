package team.e.components.db.mock;

import java.util.Collection;

import team.e.components.db.ISession;
import team.e.components.sysfunc.timetable.Course;
import team.e.components.sysfunc.timetable.Session;

public class SessionQueries implements ISession {

	@Override
	public Collection<Session> getCompulsorySessionsForCourse(String courseID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Course> getAllCoursesForStudent(String studentID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Session> getAllSessionsForStudent(String studentID) {
		// TODO Auto-generated method stub
		return null;
	}

}
