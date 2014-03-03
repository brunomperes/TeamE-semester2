package uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable;

public class StudentHasTimetableSlot implements IIdentifiable {

	private String id;
	private String studentId;
	private String timetableSlotId;

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
