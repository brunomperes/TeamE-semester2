package team.e.components.lecturer;

import java.util.Collection;

import team.e.components.sysfunc.mycampus.MyCampusFunctions;
import team.e.components.sysfunc.repository.IFunctionRepository;
import team.e.components.sysfunc.timetable.Course;
import team.e.components.sysfunc.timetable.CourseFunctions;
import team.e.components.sysfunc.timetable.Session;
import team.e.components.sysfunc.timetable.SessionFunctions;
import team.e.components.users.UserAccess;

public class LecturerAccess extends UserAccess {
	public boolean addSessionToCourse(Session session, String courseID) {
		IFunctionRepository funcRepo = getFuncRepo();
		CourseFunctions crFuncs = (CourseFunctions) funcRepo
				.getFunction(CourseFunctions.class);

		return crFuncs.addSession(session, courseID);
	}

	public Collection<Course> getMyCampusCourses() {
		IFunctionRepository funcRepo = getFuncRepo();
		MyCampusFunctions mcFuncs = (MyCampusFunctions) funcRepo
				.getFunction(MyCampusFunctions.class);
		// System.out.println("sagdf: " + mcFuncs);

		return mcFuncs.getCourseList();
	}

	public boolean importMyCampusCourse(String courseID) {
		IFunctionRepository funcRepo = getFuncRepo();
		MyCampusFunctions mcFuncs = (MyCampusFunctions) funcRepo
				.getFunction(MyCampusFunctions.class);
		CourseFunctions crFuncs = (CourseFunctions) funcRepo
				.getFunction(CourseFunctions.class);

		Collection<Course> courses = mcFuncs.getCourseList();
		for (Course course : courses) {
			if (course.getId().equals(courseID)) {
				crFuncs.addCourse(course);
				return true;
			}
		}

		return false;
	}

	public boolean setSessionFrequency(String sessionID, int frequency) {
		IFunctionRepository funcRepo = getFuncRepo();
		SessionFunctions sesFuncs = (SessionFunctions) funcRepo
				.getFunction(SessionFunctions.class);

		Session session = sesFuncs.getSession(sessionID);
		if (session == null) return false;
		session.setFrequency(frequency);

		return true;
	}
}