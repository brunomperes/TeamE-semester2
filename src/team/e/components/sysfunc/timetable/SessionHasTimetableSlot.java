package team.e.components.sysfunc.timetable;

public class SessionHasTimetableSlot implements IIdentifiable {

	private String id;
	private String sessionId;
	private String timetableSlotId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTimetableSlotId() {
		return timetableSlotId;
	}

	public void setTimetableSlotId(String timetableSlotId) {
		this.timetableSlotId = timetableSlotId;
	}

}
