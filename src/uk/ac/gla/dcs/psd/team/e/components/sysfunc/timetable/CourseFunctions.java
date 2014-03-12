package uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import uk.ac.gla.dcs.psd.team.e.components.db.IDatabase;

public class CourseFunctions {

	private IDatabase db;

	public CourseFunctions(IDatabase db) {
		this.db = db;
	}

	public Collection<Session> getCourseSessions(String courseID) {
		Collection<IIdentifiable> courseSession = db
				.getAll(CourseHasSession.class);
		Collection<Session> result = new ArrayList<Session>();

		for (IIdentifiable currentSessionID : courseSession) {
			CourseHasSession intermediate = (CourseHasSession) currentSessionID;
			Session s = (Session) db.get(intermediate.getSessionId(),
					Session.class);
			// Checks if it returned something
			if (s != null) {
				result.add(s);
			}
		}

		return result;
	}

	public boolean addSession(Session newSession, String courseID) {

		addSessionToCourse(newSession, courseID);

		return false;
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
		CourseHasSession object = new CourseHasSession();
		object.setCourseId(courseID);
		object.setSessionId(newSession.getId());
		boolean response = db.add(object, CourseHasSession.class);

		return response;
	}

	public boolean addCourse(Course newCourse) {
		boolean result = db.add(newCourse, Course.class);
		return result;
	}
	
	/**
	 * @return 2 integer array, first integer indicating hour, the second weekday
	 */
	public ArrayList<int[]> checkClashes(Course course) {
		HashMap<Time, String> timetableStore = new HashMap<Time, String>();
		List<IIdentifiable> courseSessions = db.getAll(CourseHasSession.class);
		ArrayList<String> currentSessions = new ArrayList<String>();
		ArrayList<String> otherSessions = new ArrayList<String>();
		for (IIdentifiable i : courseSessions) {
			CourseHasSession j = (CourseHasSession) i;
			if (((Session) db.get(j.getSessionId(), Session.class))
					.isCompulsory()) {
				if (j.getCourseId().equals(course.getId())) {
					currentSessions.add(j.getSessionId());
				} else {
					otherSessions.add(j.getSessionId());
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
						timetableStore.put(new Time(hour + count, hour + count
								+ 1, wd), id);
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
						if (timetableStore.containsKey(new Time(hour + count, hour
								+ count + 1, wd))){
							int k[] = {hour, wd};
							retList.add(k);
						}
					}
				}
			}
		}
		return retList;
	}

	private class Time {
		int startHours;
		int endHours;
		int weekday;

		public Time(int sh, int eh, int wd) {
			startHours = sh;
			endHours = eh;
			weekday = wd;
		}

		public int getstartHours() {
			return startHours;
		}

		public int getendHours() {
			return endHours;
		}
		
		public boolean equals(Time t) {
			return startHours == t.startHours && endHours == t.endHours && weekday == t.weekday;
		}
	}
}
