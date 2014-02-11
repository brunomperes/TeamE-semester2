package team.e.components.sysfunc.timetable;

import java.util.Collection;
import java.util.Date;

public class TimetableSlot {
	private String id;
	private Date beginDate;
	private String location;
	private Collection<String> studentIDs;
	private String tutorID;

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

	public Collection<String> getStudentIDs() {
		return studentIDs;
	}

	public void setStudentIDs(Collection<String> studentIDs) {
		this.studentIDs = studentIDs;
	}

	public String getTutorID() {
		return tutorID;
	}

	public void setTutorID(String tutorID) {
		this.tutorID = tutorID;
	}

}
