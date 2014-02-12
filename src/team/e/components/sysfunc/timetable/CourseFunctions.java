package team.e.components.sysfunc.timetable;

import java.util.ArrayList;
import java.util.Collection;

import team.e.components.db.IDatabase;

public class CourseFunctions {

	private IDatabase db;

	public CourseFunctions(IDatabase db) {
		this.db = db;
	}

	public Collection<Session> getSessions(String courseID) {
		Collection<Session> sessions;
		
		sessions = getAllSessions(courseID);

		return sessions;
	}

	public boolean addSession(Session newSession, String courseID) {
		
		addSessionToCourse(newSession, courseID);
		
		return false;
	}
	
	public Collection<Session> getAllSessions(String courseID) {
		
		Collection<IIdentifiable> courseSession = db.getAll(CourseHasSession.class);
		Collection<Session> result = new ArrayList<Session>();
		
		for (IIdentifiable currentSessionID : courseSession) {
			CourseHasSession intermediate = (CourseHasSession) currentSessionID;
			Session s = (Session) db.get(intermediate.getSessionId(), Session.class);
			result.add(s);
		}
		
		return result;
	}

	public boolean addSessionToCourse(Session newSession, String courseID) {
		CourseHasSession object = new CourseHasSession();
		object.setCourseId(courseID);
		object.setSessionId(newSession.getId());
		boolean response = db.add(object, CourseHasSession.class);
		
		return response;
	}
}
