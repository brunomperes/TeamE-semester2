package team.e.components.sysfunc.mycampus;

import java.util.Collection;

import team.e.components.mycampus.IMyCampus;
import team.e.components.sysfunc.timetable.Course;
import team.e.components.sysfunc.timetable.Session;

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
