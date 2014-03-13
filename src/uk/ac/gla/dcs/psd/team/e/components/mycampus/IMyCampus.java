package uk.ac.gla.dcs.psd.team.e.components.mycampus;

import java.util.Collection;

import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Course;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Session;
import uk.ac.gla.dcs.psd.team.e.components.users.auth.IAuthenticator;

public interface IMyCampus extends IAuthenticator {
	
	public Collection<Course> getCourseList();
	public Collection<Session> getCourseSessions(String courseID);
	
}
