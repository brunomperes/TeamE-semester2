package uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable;

import java.util.ArrayList;
import java.util.List;

import uk.ac.gla.dcs.psd.team.e.components.db.IDatabase;
import uk.ac.gla.dcs.psd.team.e.components.db.IIdentifiable;

public class TimetableSlotFunctions {
	
	private IDatabase db;
	
	public TimetableSlotFunctions(IDatabase db){
		this.db = db;
	}
	
	public boolean addStudentToSlot(String studentID, String slotID){

		StudentHasTimetableSlot studentSlot = new StudentHasTimetableSlot(studentID,slotID);
		
		boolean response = db.add(studentSlot, StudentHasTimetableSlot.class);
		
		return response;
	}

	public boolean assignRoomToSlot(String newRoom, String slotID) {
		
		TimetableSlot slot = (TimetableSlot) db.get(slotID, TimetableSlot.class);
		
		slot.setLocation(newRoom);
		
		return db.update(slot, TimetableSlot.class);
	}
	
	public List<TimetableSlot> findTimeTableSlotForStudent(String studentID) {
		
		List<IIdentifiable> relations = db.getAll(StudentHasTimetableSlot.class);
		
		List<StudentHasTimetableSlot> filteredRelations = new ArrayList<StudentHasTimetableSlot>();
		for (IIdentifiable i : relations) {
			if (((StudentHasTimetableSlot) i).getStudentId().equals(studentID)) {
				filteredRelations.add((StudentHasTimetableSlot) i);
			}
		}
		//filteredRelations contains only relations of the desired student at this point
		
		List<TimetableSlot> result = new ArrayList<TimetableSlot>();
		for (StudentHasTimetableSlot i : filteredRelations) {
			result.add((TimetableSlot) db.get(i.getTimetableSlotId(), TimetableSlot.class));
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
		
		List<TimetableSlot> lecturerSlots = findTimeTableSlotForLecturer(lecturerID);
		List<TimetableSlot> result = new ArrayList<TimetableSlot>();
		for (TimetableSlot s : lecturerSlots) {
			if (s.getSessionID().equals(sessionID)) {
				result.add(s);
			}
		}
		
		return result;
	}

}
