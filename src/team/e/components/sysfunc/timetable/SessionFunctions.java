package team.e.components.sysfunc.timetable;

import java.util.Collection;

import team.e.components.db.IDatabase;

public class SessionFunctions {
	public Collection<Session> getCompulsoryUnbookedSessions(String studentID){
		IDatabase d = null;
		d.getAll(Session.class);
		return null;
	}
}
