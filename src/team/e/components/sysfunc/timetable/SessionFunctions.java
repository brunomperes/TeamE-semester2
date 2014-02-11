package team.e.components.sysfunc.timetable;

import java.util.ArrayList;
import java.util.Collection;

import team.e.components.db.IDatabase;
import team.e.components.db.ISession;

public class SessionFunctions {
	
	private IDatabase db;
	private ISession sessionInterface;
	
	public SessionFunctions(IDatabase db, ISession sessionInterface){
		this.db = db;
		this.sessionInterface = sessionInterface;
	}
	
	public Session getSession(String sessionID){

		Session s = (Session) db.get(sessionID, Session.class);
		
		return s;
	}
	
	public Collection<Session> getCompulsoryUnbookedSessions(String studentID){
		
		Collection<Course> coursesEnroledByStudent = sessionInterface.getAllCoursesForStudent(studentID);
		
		Collection<Session> allCompulsorySessionForAllCourses = new ArrayList<Session>();
		for (Course course : coursesEnroledByStudent) {
			Collection<Session> currentCompulsorySessions = sessionInterface.getCompulsorySessionsForCourse(course.getId());
			for (Session session : currentCompulsorySessions) {
				allCompulsorySessionForAllCourses.add(session);
			}
		}
		
		Collection<Session> sessionsStudentIsIn = sessionInterface.getAllSessionsForStudent(studentID);
		allCompulsorySessionForAllCourses.removeAll(sessionsStudentIsIn);
		
		return allCompulsorySessionForAllCourses;
	}
}
