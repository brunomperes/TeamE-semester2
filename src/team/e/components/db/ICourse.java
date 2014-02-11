package team.e.components.db;

import java.util.Collection;

import team.e.components.sysfunc.timetable.Session;

/**
 * Complements the functionalities of the IDatabase interface for Courses 
 * 
 * @author Bruno Peres
 *
 */
public interface ICourse {

	public Collection<Session> getAllSessions(String courseID);
	
	public boolean addSessionToCourse(Session newSession, String courseID);

}
