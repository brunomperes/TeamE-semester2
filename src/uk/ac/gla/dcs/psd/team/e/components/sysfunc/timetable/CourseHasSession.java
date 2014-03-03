package uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable;

public class CourseHasSession implements IIdentifiable {
	private String id;
	private String courseId;
	private String sessionId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String studentId) {
		this.courseId = studentId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
