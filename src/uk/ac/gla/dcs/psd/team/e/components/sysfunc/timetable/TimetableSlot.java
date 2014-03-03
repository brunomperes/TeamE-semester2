package uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable;

import java.util.Date;

public class TimetableSlot implements IIdentifiable {
	private String id;
	private Date beginDate;
	private String location;
	private String tutorID;

	public TimetableSlot(String id, Date beginDate, String location,
			String tutorID) {
		this.id = id;
		this.beginDate = (Date) beginDate.clone();
		this.location = location;
		this.tutorID = tutorID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTutorID() {
		return tutorID;
	}

	public void setTutorID(String tutorID) {
		this.tutorID = tutorID;
	}

}
