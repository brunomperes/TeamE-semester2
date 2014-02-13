package team.e.components.lecturer;

import java.util.Collection;

import team.e.components.sysfunc.mycampus.MyCampusFunctions;
import team.e.components.sysfunc.repository.IFunctionRepository;
import team.e.components.sysfunc.timetable.Course;
import team.e.components.sysfunc.timetable.CourseFunctions;
import team.e.components.sysfunc.timetable.Session;
import team.e.components.sysfunc.timetable.TimetableSlotFunctions;
import team.e.components.users.UserAccess;

public class LecturerAccess extends UserAccess {
	public boolean addSessionToCourse(Session session, String courseID) {
		IFunctionRepository funcRepo = getFuncRepo();
		CourseFunctions crFuncs = (CourseFunctions) funcRepo.getFunction(TimetableSlotFunctions.class);
		
		return crFuncs.addSession(session, courseID);
	}
	
	public Collection<Course> getMyCampusCourses() {
		IFunctionRepository funcRepo = getFuncRepo();
		MyCampusFunctions mcFuncs = (MyCampusFunctions) funcRepo.getFunction(MyCampusFunctions.class);
		
		return mcFuncs.getCourseList();
	}
	
	public boolean importMyCampusCourse(String courseID) {
		IFunctionRepository funcRepo = getFuncRepo();
		MyCampusFunctions mcFuncs = (MyCampusFunctions) funcRepo.getFunction(MyCampusFunctions.class);
		CourseFunctions crFuncs = (CourseFunctions) funcRepo.getFunction(TimetableSlotFunctions.class);
		
		Collection<Course> courses = mcFuncs.getCourseList();
		for (Course course: courses) {
			if (course.getId().equals(courseID)) {
				crFuncs.addCourse(course);
				return true;
			}
		}
		
		return false;
	}
}