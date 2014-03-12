package uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable;

import uk.ac.gla.dcs.psd.team.e.components.db.IIdentifiable;

public class SessionHasTimetableSlot implements IIdentifiable {

	public SessionHasTimetableSlot(String id, String sessionId,
			String timetableSlotId) {
		super();
		this.id = id;
		this.sessionId = sessionId;
		this.timetableSlotId = timetableSlotId;
	}

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
