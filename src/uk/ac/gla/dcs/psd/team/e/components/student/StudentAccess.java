package uk.ac.gla.dcs.psd.team.e.components.student;

import java.util.Collection;

import uk.ac.gla.dcs.psd.team.e.components.sysfunc.repository.IFunctionRepository;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Session;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.SessionFunctions;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.TimetableSlotFunctions;
import uk.ac.gla.dcs.psd.team.e.components.users.UserAccess;

public class StudentAccess extends UserAccess {
	public boolean bookTimetableSlot(String slotID) {
		IFunctionRepository funcRepo = getFuncRepo();
		TimetableSlotFunctions tsFuncs = (TimetableSlotFunctions) funcRepo.getFunction(TimetableSlotFunctions.class);
		
		return tsFuncs.addStudentToSlot(getUsername(), slotID);
	}
	
	public Collection<Session> getCompulsoryUnbookedSessions() {
		IFunctionRepository funcRepo = getFuncRepo();
		SessionFunctions sesFuncs = (SessionFunctions) funcRepo.getFunction(SessionFunctions.class);
		
		return sesFuncs.getCompulsoryUnbookedSessions(getUsername());
	}
}
