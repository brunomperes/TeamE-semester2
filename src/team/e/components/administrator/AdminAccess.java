package team.e.components.administrator;

import team.e.components.sysfunc.repository.IFunctionRepository;
import team.e.components.sysfunc.timetable.SessionFunctions;
import team.e.components.sysfunc.timetable.TimetableSlotFunctions;
import team.e.components.users.UserAccess;

public class AdminAccess extends UserAccess {
	boolean assignRoomToSlot(String roomID, String slotID) {
		IFunctionRepository funcRepo = getFuncRepo();
		TimetableSlotFunctions tsFuncs = (TimetableSlotFunctions) funcRepo.getFunction(TimetableSlotFunctions.class);
		
		return tsFuncs.assignRoomToSlot(roomID, slotID);
	}
}