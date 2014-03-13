package uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable;

import java.util.ArrayList;
import java.util.Collection;

import uk.ac.gla.dcs.psd.team.e.components.db.IDatabase;
import uk.ac.gla.dcs.psd.team.e.components.db.IIdentifiable;

public class SessionFunctions {

	private IDatabase db;

	public SessionFunctions(IDatabase db) {
		this.db = db;
	}

	public Session getSession(String sessionID) {

		Session s = (Session) db.get(sessionID, Session.class);

		return s;
	}

	public Collection<Session> getCompulsoryUnbookedSessions(String studentID) {

		Collection<Course> coursesEnroledByStudent = getAllCoursesForStudent(studentID);

		Collection<Session> allCompulsorySessionForAllCourses = new ArrayList<Session>();
		for (Course course : coursesEnroledByStudent) {
			if(course != null){
				Collection<Session> currentCompulsorySessions = getCompulsorySessionsForCourse(course.getId());
				for (Session session : currentCompulsorySessions) {
					allCompulsorySessionForAllCourses.add(session);
				}
			}
		}

		Collection<Session> sessionsStudentIsIn = getAllSessionsForStudent(studentID);
		allCompulsorySessionForAllCourses.removeAll(sessionsStudentIsIn);

		return allCompulsorySessionForAllCourses;
	}

	public Collection<Session> getCompulsorySessionsForCourse(String courseID) {
		
		Collection<IIdentifiable> nonFilter = db.getAll(Session.class);
		Collection<Session> result = new ArrayList<Session>();
		
		for (IIdentifiable iIdentifiable : nonFilter) {
			Session session = (Session) iIdentifiable;
			if(session.getCourseID().equals(courseID)){
				if (session.isCompulsory()){
					result.add(session);
				}
			}
		}
		
		return result;
	}

	public Collection<Course> getAllCoursesForStudent(String studentID) {
		
		Collection<IIdentifiable> allStudentHasCourse = db.getAll(StudentHasCourse.class);
		Collection<Course> result = new ArrayList<Course>();
		
		//Filter
		for (IIdentifiable iIdentifiable : allStudentHasCourse) {
			StudentHasCourse intermediate = (StudentHasCourse) iIdentifiable;
			if(intermediate.getStudentId().equals(studentID)){
				result.add((Course) db.get(intermediate.getCourseId(), Course.class));
			}
		}
		
		return result;
	}

	/**
	 * Returns all the sessions the student currently has timetable slots
	 * @param studentID
	 * @return
	 */
	public Collection<Session> getAllSessionsForStudent(String studentID) {
		
		Collection<StudentHasTimetableSlot> slotsStudentIsIn = selectStudentHasTimetableSlotByStudentId(studentID);
		Collection<Session> result = new ArrayList<Session>();
		
		for (StudentHasTimetableSlot studentHasTimetableSlot : slotsStudentIsIn) {
			result.addAll(selectSessionByTimetable(studentHasTimetableSlot.getTimetableSlotId()));
		}
		
		return result;
	}
	
	/**
	 * Returns all the StudentHasTimetableSlot entries that's studentID matches the given studentID
	 * @param studentID
	 * @return Collection<StudentHasTimetableSlot>
	 */
	private Collection<StudentHasTimetableSlot> selectStudentHasTimetableSlotByStudentId(String studentID){
		Collection<IIdentifiable> all = db.getAll(StudentHasTimetableSlot.class);
		Collection<StudentHasTimetableSlot> result = new ArrayList<StudentHasTimetableSlot>();
		
		for (IIdentifiable one : all) {
			StudentHasTimetableSlot casted = (StudentHasTimetableSlot) one;
			if(casted.getStudentId().equals(studentID)){
				result.add(casted);
			}
		}
		
		return result;
		
	}
	
	public Collection<Session> selectSessionByTimetable(String timetableID){
		Collection<IIdentifiable> all = db.getAll(TimetableSlot.class);
		Collection<Session> result = new ArrayList<Session>();
		
		for (IIdentifiable one : all) {
			TimetableSlot casted = (TimetableSlot) one;
			if(casted.getId().equals(timetableID)){
				result.add((Session)db.get(casted.getSessionID(), Session.class));
			}
		}
		
		return result;
		
	}
}
