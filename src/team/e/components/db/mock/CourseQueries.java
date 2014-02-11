package team.e.components.db.mock;

import java.util.Collection;

import team.e.components.db.ICourse;
import team.e.components.sysfunc.timetable.Session;

public class CourseQueries implements ICourse {

	@Override
	public Collection<Session> getAllSessions(String courseID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addSessionToCourse(Session newSession, String courseID) {
		// TODO Auto-generated method stub
		return false;
	}

}
