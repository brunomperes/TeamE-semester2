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
			Collection<Session> currentCompulsorySessions = getCompulsorySessionsForCourse(course
					.getId());
			for (Session session : currentCompulsorySessions) {
				allCompulsorySessionForAllCourses.add(session);
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
			Collection<SessionHasTimetableSlot> intermediate = selectSessionHasTimetableSlotByTimetable(studentHasTimetableSlot.getTimetableSlotId());
			for (SessionHasTimetableSlot sessionHasTimetableSlot : intermediate) {
				result.add((Session) db.get(sessionHasTimetableSlot.getSessionId(), Session.class));
			}
		}
		
		return result;
	}
	
	/**
	 * A select function using the where conditions as the studentID 
	 * @param studentID
	 * @return
	 */
	public Collection<StudentHasTimetableSlot> selectStudentHasTimetableSlotByStudentId(String studentID){
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
	
	public Collection<SessionHasTimetableSlot> selectSessionHasTimetableSlotByTimetable(String timetableID){
		Collection<IIdentifiable> all = db.getAll(SessionHasTimetableSlot.class);
		Collection<SessionHasTimetableSlot> result = new ArrayList<SessionHasTimetableSlot>();
		
		for (IIdentifiable one : all) {
			SessionHasTimetableSlot casted = (SessionHasTimetableSlot) one;
			if(casted.getSessionId().equals(timetableID)){
				result.add(casted);
			}
		}
		
		return result;
		
	}
}
