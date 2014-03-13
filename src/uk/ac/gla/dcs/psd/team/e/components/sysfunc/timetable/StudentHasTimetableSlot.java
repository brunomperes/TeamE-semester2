package uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable;

import uk.ac.gla.dcs.psd.team.e.components.db.IIdentifiable;

public class StudentHasTimetableSlot implements IIdentifiable {
	static int idCounter = 0;
	
	private String id;
	private String studentId;
	private String timetableSlotId;
	
	public StudentHasTimetableSlot(String studentID, String timetableSlotId){
		this.id=Integer.toString(idCounter);
		idCounter++;
		this.studentId=studentID;
		this.timetableSlotId=timetableSlotId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getTimetableSlotId() {
		return timetableSlotId;
	}

	public void setTimetableSlotId(String timetableSlotId) {
		this.timetableSlotId = timetableSlotId;
	}

}
