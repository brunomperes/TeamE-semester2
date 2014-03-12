package uk.ac.gla.dcs.psd.team.e.components.mycampus.stub;

import java.util.ArrayList;
import java.util.Collection;

import uk.ac.gla.dcs.psd.team.e.components.mycampus.IMyCampus;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.IFunctionRepository;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Course;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.CourseHasSession;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Session;
import uk.ac.gla.dcs.psd.team.e.components.users.auth.AuthResult;
import uk.ac.gla.dcs.psd.team.e.components.users.auth.IAuthenticator;

public class MyCampusStub implements IMyCampus, IAuthenticator{
	
	private ArrayList<Course> courses;
	private ArrayList<Session> sessions;
	private ArrayList<CourseHasSession> relations;
	
	public MyCampusStub(IFunctionRepository funcRepo){
		courses = new ArrayList<Course>();
		courses.add(new Course("CS1", "PSD3", "13TS"));
		courses.add(new Course("CS2", "NS3", "22TJ"));
		
		sessions = new ArrayList<Session>();
		sessions.add(new Session("PSD3-1", "PSD3 Lab", 1, 50, 10, true));
		sessions.add(new Session("PSD3-2", "PSD3 Tutorial", 2, 50, 10, false));
		sessions.add(new Session("NSD3-1", "NS3 Lab", 1, 50, 12, true));
		
		relations = new ArrayList<CourseHasSession>();
		
		CourseHasSession relationEntry = new CourseHasSession();
		relationEntry.setCourseId("CS1");
		relationEntry.setSessionId("PSD3-1");
		relationEntry.setId("1");
		relations.add(relationEntry);
		
		relationEntry = new CourseHasSession();
		relationEntry.setCourseId("CS1");
		relationEntry.setSessionId("PSD3-2");
		relationEntry.setId("2");
		relations.add(relationEntry);
		
		relationEntry = new CourseHasSession();
		relationEntry.setCourseId("CS2");
		relationEntry.setSessionId("NS3-1");
		relationEntry.setId("3");
		relations.add(relationEntry);
	}
	
	public Collection<Course> getCourseList(){
		return courses;
	}
	
	/**
	 * Gets all the Session objects belonging to the given course.
	 */
	public Collection<Session> getCourseSessions(String courseID) {
		Collection<Session> result = new ArrayList<Session>();
		for (CourseHasSession relationEntry : relations) {
			if (relationEntry.getCourseId().equals(courseID))
				result.add(getSession(relationEntry.getSessionId()));
		}
		return result;
	}

	@Override
	public AuthResult auth(String username, String password) {
		AuthResult result=null;
		if(username=="admin"||username=="student"||username=="lecturer"){
			result=new AuthResult();
			result.success=true;
			result.userType=username;
		}
		return result;
	}
	
	/** Gets the specified session object from the stub MyCampus system. */
	private Session getSession(String sessionID) {
		for (Session s : sessions) {
			if (s.getId().equals(sessionID)) {
				return s;
			}
		}
		return null;
	}
	
}
