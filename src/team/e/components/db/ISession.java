package team.e.components.db;

import java.util.Collection;

import team.e.components.sysfunc.timetable.Course;
import team.e.components.sysfunc.timetable.Session;

public interface ISession {
	
	public Collection<Session> getCompulsorySessionsForCourse(String courseID);
	
	public Collection<Course> getAllCoursesForStudent(String studentID);
	
	public Collection<Session> getAllSessionsForStudent(String studentID);

}
