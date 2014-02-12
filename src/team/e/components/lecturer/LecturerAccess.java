package team.e.components.lecturer;

import team.e.components.sysfunc.repository.IFunctionRepository;
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
}
