package team.e.components.sysfunc.timetable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import team.e.components.db.IDatabase;

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
		return null;
	}

	public Collection<Course> getAllCoursesForStudent(String studentID) {
		return null;
	}

	/**
	 * Returns all the sessions the student currently has timetable slots
	 * @param studentID
	 * @return
	 */
	public Collection<Session> getAllSessionsForStudent(String studentID) {
		
		Collection<StudentHasTimetableSlot> slotsStudentIsIn = selectStudentHasTimetableSlotByStudentId(studentID);;
		Collection<SessionHasTimetableSlot> result = new ArrayList<SessionHasTimetableSlot>();
		
		for (StudentHasTimetableSlot studentHasTimetableSlot : slotsStudentIsIn) {
			 result.addAll(selectSessionHasTimetableSlotByTimetable(studentHasTimetableSlot.getTimetableSlotId()));
		}
		
		return null;
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
