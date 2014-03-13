package uk.ac.gla.dcs.psd.team.e.components.administrator;

import java.util.ArrayList;

import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.IFunctionRepository;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.CourseFunctions;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.TimetableSlotFunctions;
import uk.ac.gla.dcs.psd.team.e.components.users.UserAccess;

public class AdminAccess extends UserAccess {
	
	public boolean assignRoomToSlot(String roomID, String slotID) {
		IFunctionRepository funcRepo = getFuncRepo();
		TimetableSlotFunctions tsFuncs = (TimetableSlotFunctions) funcRepo.getFunction(TimetableSlotFunctions.class);
		
		return tsFuncs.assignRoomToSlot(roomID, slotID);
	}
	
	public ArrayList<int[]> checkCourseClashes(String courseID){
		IFunctionRepository funcRepo = getFuncRepo();
		CourseFunctions cFuncs=(CourseFunctions) funcRepo.getFunction(CourseFunctions.class);
		
		return cFuncs.checkClashes(courseID);
	}
}