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
		
		Course course = (Course) db.get(courseID, Course.class);
				
		Collection<String> sessionsIDs = course.getSessionsIDs();
		Collection<Session> courseSessions = new ArrayList<Session>();
		
		for (String currentSessionID : sessionsIDs) {
			Session s = (Session) db.get(currentSessionID, Session.class);
			courseSessions.add(s);
		}
		
		return courseSessions;
	}

	public boolean addSessionToCourse(Session newSession, String courseID) {
		Course course = (Course) db.get(courseID, Course.class);
		Collection<String> sessionsID = course.getSessionsIDs();
		
		sessionsID.add(newSession.getId());
		
		boolean response = db.update(course, Course.class);
		
		return response;
	}
}
