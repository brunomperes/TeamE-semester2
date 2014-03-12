package uk.ac.gla.dcs.psd.team.e.components.mycampus.stub;

import java.util.ArrayList;
import java.util.Collection;

import uk.ac.gla.dcs.psd.team.e.components.mycampus.IMyCampus;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.IFunctionRepository;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Course;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Session;
import uk.ac.gla.dcs.psd.team.e.components.users.auth.AuthResult;

public class MyCampusStub implements IMyCampus{
	
	private ArrayList<Course> courses;
	private ArrayList<Session> sessions;
	
	public MyCampusStub(IFunctionRepository funcRepo){
		courses = new ArrayList<Course>();
		courses.add(new Course("CS1", "PSD3", "13TS"));
		courses.add(new Course("CS2", "NS3", "22TJ"));
		
		sessions = new ArrayList<Session>();
		sessions.add(new Session("PSD3-1", "PSD3 Lab", 1, 50, 10, true, "CS1"));
		sessions.add(new Session("PSD3-2", "PSD3 Tutorial", 2, 50, 10, false, "CS1"));
		sessions.add(new Session("NSD3-1", "NS3 Lab", 1, 50, 12, true, "CS2"));
		
	}
	
	public Collection<Course> getCourseList(){
		return courses;
	}
	
	/**
	 * Gets all the Session objects belonging to the given course.
	 */
	public Collection<Session> getCourseSessions(String courseID) {
		Collection<Session> result = new ArrayList<Session>();
		for (Session session:sessions) {
			if(session.getCourseID().equals(courseID)){
				result.add(session);
			}
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
	
}
