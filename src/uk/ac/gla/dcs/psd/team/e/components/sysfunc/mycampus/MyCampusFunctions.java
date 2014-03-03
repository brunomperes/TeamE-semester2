package uk.ac.gla.dcs.psd.team.e.components.sysfunc.mycampus;

import java.util.Collection;

import uk.ac.gla.dcs.psd.team.e.components.mycampus.IMyCampus;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Course;
import uk.ac.gla.dcs.psd.team.e.components.sysfunc.timetable.Session;

public class MyCampusFunctions{
	
	private IMyCampus myCampusInterface;
	
	public MyCampusFunctions(IMyCampus myCampusInterface){
		this.myCampusInterface=myCampusInterface;
	}
	
	public Collection<Course> getCourseList() {
		return myCampusInterface.getCourseList();
	}

	public Collection<Session> getCourseSessions(String courseID) {
		return myCampusInterface.getCourseSessions(courseID);
	}

}
