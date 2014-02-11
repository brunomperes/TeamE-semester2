package team.e.components.mycampus;

import java.util.Collection;

import team.e.components.sysfunc.timetable.Course;
import team.e.components.sysfunc.timetable.Session;
import team.e.components.users.auth.IAuthenticator;

public interface IMyCampus extends IAuthenticator {
	
	public Collection<Course> getCourseList();
	public Collection<Session> getCourseSessions();
	
}
