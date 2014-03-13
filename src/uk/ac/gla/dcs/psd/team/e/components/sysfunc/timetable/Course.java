package uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable;

import uk.ac.gla.dcs.psd.team.e.components.db.IIdentifiable;


public class Course implements IIdentifiable {
	private String id;
	private String name;
	private String lecturerID;

	public Course(String id, String name, String lecturerID) {
		this.id = id;
		this.name = name;
		this.lecturerID = lecturerID;
	}
	
	/** for use with tests only!*/
	public Course(String name, String lecturerID) {
		this.name = name;
		this.lecturerID = lecturerID;
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

}
