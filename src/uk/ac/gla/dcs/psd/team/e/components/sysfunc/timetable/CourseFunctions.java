package uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import uk.ac.gla.dcs.psd.team.e.components.db.IDatabase;
import uk.ac.gla.dcs.psd.team.e.components.db.IIdentifiable;

public class CourseFunctions {

	private IDatabase db;

	public CourseFunctions(IDatabase db) {
		this.db = db;
	}

	public Collection<Session> getCourseSessions(String courseID) {
		Collection<IIdentifiable> sessions = db.getAll(Session.class);
		Collection<Session> result = new ArrayList<Session>();

		for (IIdentifiable session : sessions) {
			Session intermediate = (Session) session;
			// Checks if it returned something
			if (intermediate.getCourseID().equals(courseID)) {
				result.add(intermediate);
			}
		}

		return result;
	}

	public Collection<Session> getAllSessions() {

		Collection<IIdentifiable> courseSession = db.getAll(Course.class);
		Collection<Session> result = new ArrayList<Session>();

		for (IIdentifiable currentSession : courseSession) {
			Session s = (Session) currentSession;
			// Checks if it returned something
			if (s != null) {
				result.add(s);
			}
		}

		return result;
	}

	public boolean addSessionToCourse(Session newSession, String courseID) {
		newSession.setCourseID(courseID);
		boolean response = db.add(newSession, Session.class);
		return response;
	}

	public boolean addCourse(Course newCourse) {
		return db.add(newCourse, Course.class);
	}
	
	/**
	 * @return 2 integer array, first integer indicating hour, the second weekday
	 */
	public ArrayList<int[]> checkClashes(String courseId) {
		Course course=(Course)db.get(courseId, Course.class);
		HashMap<String, String> timetableStore = new HashMap<String, String>();
		List<IIdentifiable> sessions = db.getAll(Session.class);
		ArrayList<String> currentSessions = new ArrayList<String>();
		ArrayList<String> otherSessions = new ArrayList<String>();
		for (IIdentifiable i : sessions) {
			Session j = (Session) i;
			if (j.isCompulsory()) {
				if (j.getCourseID().equals(course.getId())) {
					currentSessions.add(j.getId());
				} else {
					otherSessions.add(j.getId());
				}
			}
		}

		List<IIdentifiable> timetableSlots = db.getAll(TimetableSlot.class);
		for (String id : currentSessions) {
			Session thisSession = (Session) db.get(id, Session.class);
			int duration = thisSession.getDurationMinutes() / 60;
			for (IIdentifiable i : timetableSlots) {
				TimetableSlot j = (TimetableSlot) i;
				if (id.equals(j.getSessionID())) {
					int hour = j.getBeginDate().getHours();
					int wd = j.getWeekday();
					for (int count = 0; count < duration; count++) {
						timetableStore.put((Integer.toString(hour) + Integer.toString(wd)), id);
					}
				}
			}
		}
		
		ArrayList<int[]> retList = new ArrayList<int[]>();
		for (String id : otherSessions) {
			Session thisSession = (Session) db.get(id, Session.class);
			int duration = thisSession.getDurationMinutes() / 60;
			for (IIdentifiable i : timetableSlots) {
				TimetableSlot j = (TimetableSlot) i;
				if (id.equals(j.getSessionID())) {
					int hour = j.getBeginDate().getHours();
					int wd = j.getWeekday();
					for (int count = 0; count < duration; count++) {
						if (timetableStore.containsKey((Integer.toString((hour+count)) + Integer.toString(wd)))){
							int k[] = {hour, wd};
							retList.add(k);
						}
					}
				}
			}
		}
		return retList;
	}
}