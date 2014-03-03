package team.e.components.sysfunc.timetable;

public class StudentHasCourse implements IIdentifiable {
	private String id;
	private String courseId;
	private String studentId;

	public StudentHasCourse(String id, String courseId, String studentId) {
		super();
		this.id = id;
		this.courseId = courseId;
		this.studentId = studentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}
