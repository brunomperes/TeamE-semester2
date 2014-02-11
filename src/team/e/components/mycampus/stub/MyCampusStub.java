package team.e.components.mycampus.stub;

import java.util.ArrayList;
import java.util.Collection;

import team.e.components.sysfunc.timetable.Course;
import team.e.components.sysfunc.timetable.Session;

public class MyCampusStub implements IMyCampus{
	
	private ArrayList<Course> courses;
	private ArrayList<Session> sessions;
	
	public MyCampusStub(){
		ArrayList<String> psd3SessionIDs= new ArrayList<String>();
		psd3SessionIDs.add("PSD3-1");
		psd3SessionIDs.add("PSD3-2");
		courses.add(new Course("CS1", "PSD3", "13TS", psd3SessionIDs));
		ArrayList<String> ns3SessionIDs= new ArrayList<String>();
		psd3SessionIDs.add("NS3-1");
		courses.add(new Course("CS2", "NS3", "22TJ", ns3SessionIDs));
		
		sessions.add(new Session("PSD3-1", "PSD3 Lab", new ArrayList<String>(), 1, 50, 12, true));
		sessions.add(new Session("PSD3-2", "PSD3 Tutorial", new ArrayList<String>(), 2, 50, 10, false));
		sessions.add(new Session("NSD3-1", "NS3 Lab", new ArrayList<String>(), 1, 50, 12, true));
	}
	
	public boolean authenticate(String username, String password){
		return true;
	}
	
	public Collection<Course> getCourseList(){
		return courses;
	}
	
	public Collection<Session> getCoruseSession(String courseID) throws Exception{
		ArrayList<Session> sessions=new ArrayList<Session>();
		int courseIndex=courses.indexOf(courseID);
		if (courseIndex==-1){
			throw new Exception();
		}
		Course course=courses.get(courseIndex);
		for(Session session:sessions){
			for(String courseSessionID:course.getSessionsIDs()){
				if(session.getId()==courseSessionID){
					sessions.add(session);
				}
			}
		}
		return sessions;
	}
	
}
