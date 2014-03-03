package team.e.components.sysfunc.timetable;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<TimetableSlot> findTimeTableSlotForStudent(String studentID) {
		
		List<TimetableSlot> result = new ArrayList<TimetableSlot>();
		List<IIdentifiable> queryResult = (List<IIdentifiable>) db.getAll(TimetableSlot.class);
		List<StudentHasTimetableSlot> intermediate = new ArrayList<StudentHasTimetableSlot>();
		
		for (IIdentifiable iIdentifiable : queryResult) {
			intermediate.add((StudentHasTimetableSlot) db.get(iIdentifiable.getId(), StudentHasTimetableSlot.class) ); 
		}
		
		for (StudentHasTimetableSlot studentHasTimetableSlot : intermediate) {
			if (studentHasTimetableSlot.getStudentId().equals(studentID)){
				result.add((TimetableSlot) db.get(studentHasTimetableSlot.getTimetableSlotId(), TimetableSlot.class));
			}
		}
		
		return result;
	}

	public List<TimetableSlot> findTimeTableSlotForLecturer(String lecturerID) {

		List<TimetableSlot> result = new ArrayList<TimetableSlot>();
		List<IIdentifiable> queryResult = (List<IIdentifiable>) db
				.getAll(TimetableSlot.class);
		List<TimetableSlot> intermediate = new ArrayList<TimetableSlot>();

		for (IIdentifiable iIdentifiable : queryResult) {
			intermediate.add((TimetableSlot) iIdentifiable);
		}

		for (TimetableSlot lecturerHasTimetableSlot : intermediate) {
			if (lecturerHasTimetableSlot.getTutorID().equals(lecturerID)) {
				result.add(lecturerHasTimetableSlot);
			}
		}

		return result;
	}

	public List<TimetableSlot> findTimeTableSlotForLecturerAndSession(
			String lecturerID, String sessionID) {
		List<TimetableSlot> result = new ArrayList<TimetableSlot>();
		List<IIdentifiable> queryResult = (List<IIdentifiable>) db
				.getAll(TimetableSlot.class);
		List<TimetableSlot> intermediate = new ArrayList<TimetableSlot>();

		for (IIdentifiable iIdentifiable : queryResult) {
			intermediate.add((TimetableSlot) iIdentifiable);
		}

		for (TimetableSlot lecturerHasTimetableSlot : intermediate) {
			if (lecturerHasTimetableSlot.getTutorID().equals(lecturerID) && lecturerHasTimetableSlot.getTutorID().equals(sessionID)) {
				result.add(lecturerHasTimetableSlot);
			}
		}

		return result;
	}

}
