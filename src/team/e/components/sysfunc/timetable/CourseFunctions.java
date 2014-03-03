package team.e.components.sysfunc.timetable;

import java.util.ArrayList;
import java.util.Collection;

import team.e.components.db.IDatabase;

public class CourseFunctions {

	private IDatabase db;

	public CourseFunctions(IDatabase db) {
		this.db = db;
	}

	public Collection<Session> getCourseSessions(String courseID) {
		Collection<IIdentifiable> courseSession = db.getAll(CourseHasSession.class);
		Collection<Session> result = new ArrayList<Session>();
		
		for (IIdentifiable currentSessionID : courseSession) {
			CourseHasSession intermediate = (CourseHasSession) currentSessionID;
			Session s = (Session) db.get(intermediate.getSessionId(), Session.class);
			// Checks if it returned something
			if (s != null){
				result.add(s);
			}
		}
		
		return result;
	}

	public boolean addSession(Session newSession, String courseID) {
		
		addSessionToCourse(newSession, courseID);
		
		return false;
	}
	
	public Collection<Session> getAllSessions() {
		
		Collection<IIdentifiable> courseSession = db.getAll(Course.class);
		Collection<Session> result = new ArrayList<Session>();
		
		for (IIdentifiable currentSession : courseSession) {
			Session s = (Session) currentSession;
			// Checks if it returned something
			if (s != null){
				result.add(s);
			}
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
	
	public boolean addCourse(Course newCourse) {
		boolean result = db.add(newCourse, Course.class);
		return result;
	}
}
