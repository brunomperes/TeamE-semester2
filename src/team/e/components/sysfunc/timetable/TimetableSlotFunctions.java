package team.e.components.sysfunc.timetable;

import team.e.components.db.IDatabase;

public class TimetableSlotFunctions {
	
	private IDatabase db;
	
	public TimetableSlotFunctions(IDatabase db){
		this.db = db;
	}
	
	public boolean addStudentToSlot(String studentID, String slotID){

		StudentHasTimetableSlot studentSlot = new StudentHasTimetableSlot();
		studentSlot.setStudentId(studentID);
		studentSlot.setTimetableSlotId(slotID);
		
		boolean response = db.add(studentSlot, StudentHasTimetableSlot.class);
		
		return response;
	}

	public boolean assignRoomToSlot(String newRoom, String slotID) {
		
		TimetableSlot slot = (TimetableSlot) db.get(slotID, TimetableSlot.class);
		
		slot.setLocation(newRoom);
		
		return db.update(slot, TimetableSlot.class);
	}

}
