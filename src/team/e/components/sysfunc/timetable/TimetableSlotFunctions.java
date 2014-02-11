package team.e.components.sysfunc.timetable;

import java.util.Collection;

import team.e.components.db.IDatabase;

public class TimetableSlotFunctions {
	
	private IDatabase db;
	
	public TimetableSlotFunctions(IDatabase db){
		this.db = db;
	}
	
	public boolean addStudentToSlot(String studentID, String slotID){

		TimetableSlot slot = (TimetableSlot) db.get(slotID, TimetableSlot.class);

		Collection<String> currentStudents = slot.getStudentIDs();
		currentStudents.add(studentID);
		slot.setStudentIDs(currentStudents);
		
		return db.update(slot, slotID);
	}

	public boolean assignRoomToSlot(String newRoom, String slotID) {
		
		TimetableSlot slot = (TimetableSlot) db.get(slotID, TimetableSlot.class);
		
		slot.setLocation(newRoom);
		
		return db.update(slot, slotID);
	}

}
