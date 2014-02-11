package team.e.components.sysfunc.timetable;

import java.util.Collection;

import team.e.components.db.ICourse;

public class CourseFunctions {

	private ICourse courseInterface;

	public CourseFunctions(ICourse courseInterface) {
		this.courseInterface = courseInterface;
	}

	public Collection<Session> getSessions(String courseID) {
		Collection<Session> sessions;
		
		sessions = courseInterface.getAllSessions(courseID);

		return sessions;
	}

	public boolean addSession(Session newSession, String courseID) {
		
		courseInterface.addSessionToCourse(newSession, courseID);
		
		return false;
	}
}
