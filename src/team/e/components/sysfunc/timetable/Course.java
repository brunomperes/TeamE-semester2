package team.e.components.sysfunc.timetable;

import java.util.Collection;

public class Course {
	private String id;
	private String name;
	private String lecturerID;
	private Collection<String> sessionsIDs;

	public Course(String id, String name, String lecturerID,
			Collection<String> sessionsIDs) {
		this.id = id;
		this.name = name;
		this.lecturerID = lecturerID;
		this.sessionsIDs = sessionsIDs;
	}

	public Course(String name, String lecturerID, Collection<String> sessionsIDs) {
		this.name = name;
		this.lecturerID = lecturerID;
		this.sessionsIDs = sessionsIDs;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLecturerID() {
		return lecturerID;
	}

	public void setLecturerID(String lecturerID) {
		this.lecturerID = lecturerID;
	}

	public Collection<String> getSessionsIDs() {
		return sessionsIDs;
	}

	public void setSessionsIDs(Collection<String> sessionsIDs) {
		this.sessionsIDs = sessionsIDs;
	}

}
